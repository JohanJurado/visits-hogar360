package com.pragma.hogar360_microservice_visits.domain.usecases;

import com.pragma.hogar360_microservice_visits.domain.exceptions.*;
import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.domain.ports.out.*;
import com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants;
import com.pragma.hogar360_microservice_visits.domain.utils.constants.ValidationConstants;
import com.pragma.hogar360_microservice_visits.domain.utils.validations.GlobalValidations;
import com.pragma.hogar360_microservice_visits.domain.utils.validations.SchedulerValidations;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_visits.utils.constants.SchedulerTestConstants.*;
import static com.pragma.hogar360_microservice_visits.utils.testdata.TestDataScheduler.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchedulerUseCaseTest {

    @Mock
    private ISchedulerPersistencePort schedulerPersistencePort;

    @Mock
    private IHouseFeignClientPort houseFeignClientPort;

    @Mock
    private ISecurityServicePort securityServicePort;

    @InjectMocks
    private SchedulerUseCase schedulerUseCase;

    @Test
    void save_WithValidScheduler_ShouldSaveSuccessfully() {
        SchedulerModel validScheduler = getValidScheduler();
        when(securityServicePort.getAuthenticatedEmail()).thenReturn(SELLER_EMAIL);
        when(houseFeignClientPort.existHouseByEmailSeller(SELLER_EMAIL, VALID_ID)).thenReturn(true);
        when(schedulerPersistencePort.existByIdAndRangeTime(VALID_ID, VALID_START_DATE, VALID_END_DATE)).thenReturn(false);

        schedulerUseCase.save(validScheduler);

        verify(schedulerPersistencePort).save(validScheduler);
        assertEquals(SELLER_EMAIL, validScheduler.getEmailSeller());
    }

    @Test
    void save_WithNonExistingHouse_ShouldThrowHouseNotFoundException() {
        SchedulerModel scheduler = getValidScheduler();
        when(securityServicePort.getAuthenticatedEmail()).thenReturn(SELLER_EMAIL);
        when(houseFeignClientPort.existHouseByEmailSeller(SELLER_EMAIL, VALID_ID)).thenReturn(false);

        assertThrows(HouseNotFoundException.class, () -> schedulerUseCase.save(scheduler));
        verify(schedulerPersistencePort, never()).save(any());
    }

    @Test
    void save_WithExistingDateRange_ShouldThrowDateRangeAlreadyExistException() {
        SchedulerModel scheduler = getSchedulerWithExistingRange();
        when(securityServicePort.getAuthenticatedEmail()).thenReturn(SELLER_EMAIL);
        when(houseFeignClientPort.existHouseByEmailSeller(SELLER_EMAIL, VALID_ID)).thenReturn(true);
        when(schedulerPersistencePort.existByIdAndRangeTime(VALID_ID, VALID_START_DATE, VALID_END_DATE)).thenReturn(true);

        assertThrows(DateRangeAlreadyExistException.class, () -> schedulerUseCase.save(scheduler));
        verify(schedulerPersistencePort, never()).save(any());
    }

    @Test
    void save_WithInvalidDateRange_ShouldThrowInvalidDateRangeException() {
        SchedulerModel scheduler = getSchedulerWithInvalidRange();

        assertThrows(InvalidDateRangeException.class, () -> schedulerUseCase.save(scheduler));
        verify(schedulerPersistencePort, never()).save(any());
    }

    @Test
    void save_WithExceededLimitDate_ShouldThrowScheduleExceedsLimitException() {
        SchedulerModel scheduler = getSchedulerWithExceededLimit();

        assertThrows(ScheduleExceedsLimitException.class, () -> schedulerUseCase.save(scheduler));
        verify(schedulerPersistencePort, never()).save(any());
    }

    @Test
    void save_WithNullHouseId_ShouldThrowIdHouseCannotBeNullException() {
        SchedulerModel scheduler = getSchedulerWithNullHouseId();

        assertThrows(IdHouseCannotBeNullException.class, () -> schedulerUseCase.save(scheduler));
        verify(schedulerPersistencePort, never()).save(any());
    }

    @Test
    void save_WithNullStartDate_ShouldThrowStartDateCannotBeNullException() {
        SchedulerModel scheduler = getSchedulerWithNullStartDate();

        assertThrows(StartDateCannotBeNullException.class, () -> schedulerUseCase.save(scheduler));
        verify(schedulerPersistencePort, never()).save(any());
    }

    @Test
    void save_WithNullEndDate_ShouldThrowEndDateCannotBeNullException() {
        SchedulerModel scheduler = getSchedulerWithNullEndDate();

        assertThrows(EndDateCannotBeNullException.class, () -> schedulerUseCase.save(scheduler));
        verify(schedulerPersistencePort, never()).save(any());
    }

    @Test
    @DisplayName("Test DomainConstants Constructor ThrowsIllegalStateException")
    void testDomainConstantsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<DomainConstants> constructor = DomainConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }

    @Test
    @DisplayName("Test ValidationConstants Constructor ThrowsIllegalStateException")
    void testValidationConstantsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<ValidationConstants> constructor = ValidationConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }

    @Test
    @DisplayName("Test GlobalValidations Constructor ThrowsIllegalStateException")
    void testGlobalValidationsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<GlobalValidations> constructor = GlobalValidations.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }

    @Test
    @DisplayName("Test SchedulerValidations Constructor ThrowsIllegalStateException")
    void testSchedulerValidationsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<SchedulerValidations> constructor = SchedulerValidations.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }
}