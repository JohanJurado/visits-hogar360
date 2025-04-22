package com.pragma.hogar360_microservice_visits.domain.usecases;

import com.pragma.hogar360_microservice_visits.domain.exceptions.*;
import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;
import com.pragma.hogar360_microservice_visits.domain.ports.out.ISchedulerPersistencePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.IVisitPersistencePort;
import com.pragma.hogar360_microservice_visits.domain.utils.validations.VisitValidations;
import com.pragma.hogar360_microservice_visits.utils.testdata.TestDataVisit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_visits.utils.constants.VisitTestConstants.*;
import static com.pragma.hogar360_microservice_visits.utils.testdata.TestDataVisit.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitUseCaseTest {

    @Mock
    private IVisitPersistencePort visitPersistencePort;

    @Mock
    private ISchedulerPersistencePort schedulerPersistencePort;

    @InjectMocks
    private VisitUseCase visitUseCase;

    @Test
    void save_WithValidVisit_ShouldSaveSuccessfully() {
        VisitModel validVisit = getValidVisit();
        when(schedulerPersistencePort.findById(VALID_ID))
                .thenReturn(Optional.of(validVisit.getSchedulerModel()));
        when(visitPersistencePort.existByEmailBuyerAndIdScheduler(VALID_EMAIL, VALID_ID))
                .thenReturn(false);

        visitUseCase.save(validVisit);

        verify(visitPersistencePort).saveWithVisitCountUpdate(validVisit);
        verify(schedulerPersistencePort).findById(VALID_ID);
        verify(visitPersistencePort).existByEmailBuyerAndIdScheduler(VALID_EMAIL, VALID_ID);
    }

    @Test
    void save_WithNullSchedulerId_ShouldThrowException() {
        VisitModel invalidVisit = getVisitWithNullSchedulerId();

        assertThrows(IdSchedulerCannotBeNullException.class,
                () -> visitUseCase.save(invalidVisit));
        verifyNoInteractions(schedulerPersistencePort);
        verifyNoInteractions(visitPersistencePort);
    }

    @Test
    void save_WithInvalidEmail_ShouldThrowException() {
        VisitModel invalidVisit = getVisitWithInvalidEmail();

        assertThrows(EmailNotAllowedException.class,
                () -> visitUseCase.save(invalidVisit));
        verifyNoInteractions(schedulerPersistencePort);
        verifyNoInteractions(visitPersistencePort);
    }

    @Test
    void save_WithEmptyEmail_ShouldThrowException() {
        VisitModel invalidVisit = getVisitWithEmptyEmail();

        assertThrows(EmailBuyerCannotBeEmptyException.class,
                () -> visitUseCase.save(invalidVisit));
        verifyNoInteractions(schedulerPersistencePort);
        verifyNoInteractions(visitPersistencePort);
    }

    @Test
    void save_WithNullEmail_ShouldThrowException() {
        VisitModel invalidVisit = getVisitWithNullEmail();

        assertThrows(EmailBuyerCannotBeEmptyException.class,
                () -> visitUseCase.save(invalidVisit));
        verifyNoInteractions(schedulerPersistencePort);
        verifyNoInteractions(visitPersistencePort);
    }

    @Test
    void save_WithExceededVisitLimit_ShouldThrowException() {
        VisitModel invalidVisit = getVisitWithExceededLimit();
        when(schedulerPersistencePort.findById(VALID_ID))
                .thenReturn(Optional.of(invalidVisit.getSchedulerModel()));

        assertThrows(SchedulerVisitCountExceedsLimitException.class,
                () -> visitUseCase.save(invalidVisit));
        verify(schedulerPersistencePort).findById(VALID_ID);
        verify(visitPersistencePort, never()).saveWithVisitCountUpdate(any());
    }

    @Test
    void save_WithExistingEmail_ShouldThrowException() {
        VisitModel invalidVisit = getVisitWithExistingEmail();
        when(schedulerPersistencePort.findById(VALID_ID))
                .thenReturn(Optional.of(invalidVisit.getSchedulerModel()));
        when(visitPersistencePort.existByEmailBuyerAndIdScheduler(VALID_EMAIL, VALID_ID))
                .thenReturn(true);

        assertThrows(EmailBuyerAlreadyExistException.class,
                () -> visitUseCase.save(invalidVisit));
        verify(schedulerPersistencePort).findById(VALID_ID);
        verify(visitPersistencePort).existByEmailBuyerAndIdScheduler(VALID_EMAIL, VALID_ID);
        verify(visitPersistencePort, never()).saveWithVisitCountUpdate(any());
    }

    @Test
    void save_WithNonExistentScheduler_ShouldThrowException() {
        VisitModel invalidVisit = getValidVisit();
        when(schedulerPersistencePort.findById(VALID_ID))
                .thenReturn(Optional.empty());

        assertThrows(SchedulerNotFoundException.class,
                () -> visitUseCase.save(invalidVisit));
        verify(schedulerPersistencePort).findById(VALID_ID);
        verify(visitPersistencePort, never()).existByEmailBuyerAndIdScheduler(any(), any());
        verify(visitPersistencePort, never()).saveWithVisitCountUpdate(any());
    }

    @Test
    void save_ShouldNormalizeEmailToUpperCase() {
        VisitModel visit = getValidVisit();
        SchedulerModel scheduler = TestDataVisit.getValidScheduler();

        when(schedulerPersistencePort.findById(VALID_ID))
                .thenReturn(Optional.of(scheduler));
        when(visitPersistencePort.existByEmailBuyerAndIdScheduler(NORMALIZED_EMAIL, VALID_ID))
                .thenReturn(false);

        visitUseCase.save(visit);

        verify(visitPersistencePort).existByEmailBuyerAndIdScheduler(NORMALIZED_EMAIL, VALID_ID);
        verify(visitPersistencePort).saveWithVisitCountUpdate(visit);
        assertEquals(NORMALIZED_EMAIL, visit.getEmailBuyer());
    }

    @Test
    @DisplayName("Test VisitValidations Constructor ThrowsIllegalStateException")
    void testVisitValidationsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<VisitValidations> constructor = VisitValidations.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }
}