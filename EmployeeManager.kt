import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class EmployeeManager {
    private val employees = EmployeeList()
    private val attendance = AttendanceList()

    fun addEmployee(emp: Employee): Boolean = employees.addEmployee(emp)

    fun getAllEmployees(): String = employees.toString()

    fun getEmployee(id: String): Employee? = employees.getEmployee(id)

    fun deleteEmployee(id: String): Boolean = employees.deleteEmployee(id)

    fun checkIn(id: String, dateStr: String): String {
        val employee = employees.getEmployee(id)
        if (employee == null) return "No employee with ID $id."

        val checkInDateTime = parseDateTimeInput(dateStr, isCheckIn = true)
            ?: return "Invalid or future check-in date."

        val success = attendance.addCheckIn(Attendance(id, checkInDateTime))
        return if (success) "Check-in recorded." else "Already checked-in without checkout."
    }

    fun checkOut(id: String, dateStr: String): String {
        val checkOutDateTime = parseDateTimeInput(dateStr, isCheckIn = false)
            ?: return "Invalid or future check-out date."

        val success = attendance.checkOut(id, checkOutDateTime)
        return if (success) "Check-out successful." else "Check-out failed (invalid or already done)."
    }

    fun viewAttendance(): String = attendance.toString()

    fun viewSummary(): String {
        return employees.joinToString("\n") {
            "ID: ${it.id} | Name: ${it.firstName} ${it.lastName} | Total: ${attendance.summary(it.id)}"
        }
    }

    private fun parseDateTimeInput(dateStr: String, isCheckIn: Boolean): LocalDateTime? {
        return if (dateStr.isBlank()) {
            LocalDateTime.now()
        } else {
            try {
                if (isCheckIn) {
                    val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    if (date.isAfter(LocalDate.now())) return null
                    val now = LocalDateTime.now()
                    return date.atTime(now.hour, now.minute)
                } else {
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    val dateTime = LocalDateTime.parse(dateStr, formatter)
                    if (dateTime.isAfter(LocalDateTime.now())) return null
                    return dateTime
                }
            } catch (e: DateTimeParseException) {
                null
            }
        }
    }
}
