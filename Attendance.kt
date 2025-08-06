import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.Duration

class Attendance(val id: String, val checkInDateTime: LocalDateTime) {
    var checkOutDateTime: LocalDateTime? = null
    var totalWorkedTime: String = ""

    fun checkOut(time: LocalDateTime): Boolean {
        if (time.isAfter(checkInDateTime)) {
            checkOutDateTime = time
            val duration = Duration.between(checkInDateTime, time)
            totalWorkedTime = String.format(
                "%02d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart()
            )
            return true
        }
        return false
    }

    override fun toString(): String {
        val fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val outTime = checkOutDateTime?.format(fmt) ?: "Still Checked-In"
        return "ID: $id | In: ${checkInDateTime.format(fmt)} | Out: $outTime | Duration: $totalWorkedTime"
    }
}
