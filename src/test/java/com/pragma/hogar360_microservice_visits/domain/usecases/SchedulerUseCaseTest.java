package com.pragma.hogar360_microservice_visits.domain.usecases;

import com.pragma.hogar360_microservice_visits.domain.exceptions.*;
import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.domain.ports.out.*;
import com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants;
import com.pragma.hogar360_microservice_visits.domain.utils.constants.PaginationConstants;
import com.pragma.hogar360_microservice_visits.domain.utils.constants.ValidationConstants;
import com.pragma.hogar360_microservice_visits.domain.utils.pagination.Pagination;
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
import java.util.List;

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

        assertThrows(SchedulerExceedsLimitException.class, () -> schedulerUseCase.save(scheduler));
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

    @Test
    @DisplayName("Get schedulers with valid filters returns paginated results")
    void getSchedulers_WithValidFilters_ShouldReturnPaginatedResults() {
        List<SchedulerModel> mockSchedulers = getSchedulerList();
        when(schedulerPersistencePort.findSchedulersByFilters(
                VALID_ID, VALID_START_DATE, VALID_END_DATE, MAX_VISITS))
                .thenReturn(mockSchedulers);

        Pagination<SchedulerModel> result = schedulerUseCase.getSchedulers(
                VALID_START_DATE, VALID_END_DATE, VALID_ID, VALID_PAGE, PAGE_SIZE);

        assertEquals(PAGE_SIZE, result.getPageSize());
        assertEquals(VALID_PAGE, result.getPageNumber());
        assertEquals(mockSchedulers.size(), result.getContent().size());
        verify(schedulerPersistencePort).findSchedulersByFilters(
                VALID_ID, VALID_START_DATE, VALID_END_DATE, MAX_VISITS);
    }

    @Test
    @DisplayName("Get schedulers with null dates returns all results")
    void getSchedulers_WithNullDates_ShouldReturnAllResults() {
        List<SchedulerModel> mockSchedulers = getSchedulerList();
        when(schedulerPersistencePort.findSchedulersByFilters(
                VALID_ID, null, null, MAX_VISITS))
                .thenReturn(mockSchedulers);

        Pagination<SchedulerModel> result = schedulerUseCase.getSchedulers(
                null, null, VALID_ID, VALID_PAGE, PAGE_SIZE);

        assertEquals(mockSchedulers.size(), result.getContent().size());
        verify(schedulerPersistencePort).findSchedulersByFilters(
                VALID_ID, null, null, MAX_VISITS);
    }

    @Test
    @DisplayName("Get schedulers with invalid page throws PageNotFoundException")
    void getSchedulers_WithInvalidPage_ShouldThrowException() {
        List<SchedulerModel> mockSchedulers = getSchedulerList();
        when(schedulerPersistencePort.findSchedulersByFilters(
                VALID_ID, VALID_START_DATE, VALID_END_DATE, MAX_VISITS))
                .thenReturn(mockSchedulers);

        assertThrows(PageNotFoundException.class,
                () -> schedulerUseCase.getSchedulers(
                        VALID_START_DATE, VALID_END_DATE, VALID_ID, INVALID_PAGE, PAGE_SIZE));
    }

    @Test
    @DisplayName("Get schedulers with no results returns empty pagination")
    void getSchedulers_WithNoResults_ShouldReturnEmptyPagination() {
        when(schedulerPersistencePort.findSchedulersByFilters(
                VALID_ID, VALID_START_DATE, VALID_END_DATE, MAX_VISITS))
                .thenReturn(getEmptySchedulerList());

        Pagination<SchedulerModel> result = schedulerUseCase.getSchedulers(
                VALID_START_DATE, VALID_END_DATE, VALID_ID, VALID_PAGE, PAGE_SIZE);

        assertTrue(result.getContent().isEmpty());
        assertEquals(0, result.getTotalElements());
    }

    @Test
    @DisplayName("Get schedulers verifies correct order (descending by start date)")
    void getSchedulers_ShouldReturnResultsInCorrectOrder() {
        List<SchedulerModel> mockSchedulers = getSchedulerList();
        when(schedulerPersistencePort.findSchedulersByFilters(
                VALID_ID, null, null, MAX_VISITS))
                .thenReturn(mockSchedulers);

        Pagination<SchedulerModel> result = schedulerUseCase.getSchedulers(
                null, null, VALID_ID, VALID_PAGE, PAGE_SIZE);

        List<SchedulerModel> content = result.getContent();

        assertTrue(content.get(0).getStartDate().isAfter(content.get(1).getStartDate()));
        assertTrue(content.get(1).getStartDate().isAfter(content.get(2).getStartDate()));
    }

    @Test
    @DisplayName("Test PaginationConstants Constructor ThrowsIllegalStateException")
    void testPaginationConstantsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<PaginationConstants> constructor = PaginationConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }
}