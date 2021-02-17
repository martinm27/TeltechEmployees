package com.teltech.employees

import com.teltech.employees.employeeslib.mapper.generateImageUrl
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.employeeslib.model.EmployeesResponse
import com.teltech.employees.employeeslib.source.EmployeesSource
import com.teltech.employees.employeeslib.source.EmployeesSourceImpl
import com.teltech.employees.network.model.APIEmployee
import com.teltech.employees.network.service.EmployeesService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class EmployeesSourceTest {

    @Mock
    private lateinit var employeesService: EmployeesService

    private lateinit var employeesSource: EmployeesSource

    private lateinit var emptyDepartmentEmployee: APIEmployee
    private lateinit var nullAgencyEmployee: APIEmployee
    private lateinit var nullImageEmployee: APIEmployee

    private lateinit var emptyDepartmentEmployeeMapped: Employee
    private lateinit var nullAgencyEmployeeMapped: Employee
    private lateinit var nullImageEmployeeMapped: Employee

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        `when`(employeesService.getEmployees()).thenReturn(Single.just(emptyList()))

        employeesSource = EmployeesSourceImpl(employeesService)
    }

    @Test
    fun `should map to failure response when api throws exception`() {
        `when`(employeesService.getEmployees()).thenReturn(Single.error(Throwable("Error happened")))

        val testResult = employeesSource.employees().test()
        testResult.assertValue(EmployeesResponse.Failure)

        testResult.dispose()
    }

    @Test
    fun `should map to empty list when api returns empty list`() {
        `when`(employeesService.getEmployees()).thenReturn(Single.just(emptyList()))

        val testResult = employeesSource.employees().test()
        testResult.assertValue(EmployeesResponse.Success(emptyList()))

        testResult.dispose()
    }

    @Test
    fun `should map list of nulls to empty list`() {
        `when`(employeesService.getEmployees()).thenReturn(Single.just(listOf(null, null, null)))

        val testResult = employeesSource.employees().test()
        testResult.assertValue(EmployeesResponse.Success(emptyList()))

        testResult.dispose()
    }

    @Test
    fun `should map employee with empty department to unknown string`() {
        initValues()

        `when`(employeesService.getEmployees()).thenReturn(
            Single.just(listOf(emptyDepartmentEmployee))
        )

        val testResult = employeesSource.employees().test()
        testResult.assertValue(EmployeesResponse.Success(listOf(emptyDepartmentEmployeeMapped)))

        testResult.dispose()
    }

    @Test
    fun `should map employee with null agency to unknown string`() {
        initValues()

        `when`(employeesService.getEmployees()).thenReturn(Single.just(listOf(nullAgencyEmployee)))

        val testResult = employeesSource.employees().test()
        testResult.assertValue(EmployeesResponse.Success(listOf(nullAgencyEmployeeMapped)))

        testResult.dispose()
    }

    @Test
    fun `should map employee with null image to empty string`() {
        initValues()

        `when`(employeesService.getEmployees()).thenReturn(Single.just(listOf(nullImageEmployee)))
        val testResult = employeesSource.employees().test()

        testResult.assertValue(EmployeesResponse.Success(listOf(nullImageEmployeeMapped)))

        testResult.dispose()
    }

    private fun initValues() {
        emptyDepartmentEmployee = APIEmployee(
            "",
            "Dan",
            "Pannasch",
            "dan-2019",
            "VP of Marketing",
            "Studio Magus",
            "Hello, I'm Dan",
            "When I’m not at the office, I’m probably at home drinking a Mountain Dew, listening to a podcast, and pretending to know how to be an adult."
        )

        nullAgencyEmployee = APIEmployee(
            "executive team",
            "Patrick",
            "Falzon",
            "patrick",
            "General Manager",
            null,
            "Hello, I'm Patrick",
            "As General Manager, my role is made easy by the rest of the amazing team here at Teltech. In my free time, I enjoy travel, reading and long walks on the beach (with my dog, of course)."
        )

        nullImageEmployee = APIEmployee(
            "operations",
            "Nate",
            "Joel",
            null,
            "Director of Operations",
            "Some Agency",
            "Yerr, I'm Nate.",
            "You can find me listening to new music and making up random songs, belting them around the office."
        )

        emptyDepartmentEmployeeMapped = Employee(
            0,
            "N/A",
            "Dan",
            "Pannasch",
            generateImageUrl("dan-2019"),
            "VP of Marketing",
            "Studio Magus",
            "Hello, I'm Dan",
            "When I’m not at the office, I’m probably at home drinking a Mountain Dew, listening to a podcast, and pretending to know how to be an adult."
        )

        nullAgencyEmployeeMapped = Employee(
            0,
            "executive team",
            "Patrick",
            "Falzon",
            generateImageUrl("patrick"),
            "General Manager",
            "N/A",
            "Hello, I'm Patrick",
            "As General Manager, my role is made easy by the rest of the amazing team here at Teltech. In my free time, I enjoy travel, reading and long walks on the beach (with my dog, of course)."
        )

        nullImageEmployeeMapped = Employee(
            0,
            "operations",
            "Nate",
            "Joel",
            "",
            "Director of Operations",
            "Some Agency",
            "Yerr, I'm Nate.",
            "You can find me listening to new music and making up random songs, belting them around the office."
        )
    }
}
