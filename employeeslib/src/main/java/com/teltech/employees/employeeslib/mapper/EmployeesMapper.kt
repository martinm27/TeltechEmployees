package com.teltech.employees.employeeslib.mapper

import com.teltech.employees.core.extension.ifNullOrEmpty
import com.teltech.employees.employeeslib.BuildConfig
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.network.model.APIEmployee

private const val UNKNOWN = "N/A"
private const val EMPTY = ""

fun toEmployees(apiEmployees: List<APIEmployee?>) =
    apiEmployees.filterNotNull().mapIndexed(::toEmployee)

/**
 * Every UI related employee field is mapped to N/A if null or empty. Image field is mapped to
 * empty string because of image loading.
 */
fun toEmployee(index: Int, apiEmployee: APIEmployee): Employee =
    with(apiEmployee) {
        Employee(
            index,
            department.ifNullOrEmpty { UNKNOWN },
            name.ifNullOrEmpty { UNKNOWN },
            surname.ifNullOrEmpty { UNKNOWN },
            generateImageUrl(image),
            title.ifNullOrEmpty { UNKNOWN },
            agency.ifNullOrEmpty { UNKNOWN },
            intro.ifNullOrEmpty { UNKNOWN },
            description.ifNullOrEmpty { UNKNOWN },
        )
    }

fun generateImageUrl(image: String?): String {
    if (image == null || image.isEmpty() || image.isBlank()) return EMPTY

    return StringBuilder()
        .append(BuildConfig.SERVER_URL)
        .append(image)
        .toString()
}
