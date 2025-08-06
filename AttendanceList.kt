import java.time.Duration
import java.time.LocalDateTime
class AttendanceList : ArrayList<Attendance>() {

    fun addCheckIn(attendance: Attendance): Boolean {
        if (hasCheckedIn(attendance.id)) return false
        return this.add(attendance)
    }

    fun hasCheckedIn(id: String): Boolean {
        // already checkedIn
        return this.any { it.id == id && it.checkOutDateTime == null }
    }

    fun hasCheckedOut(id: String): Boolean {
        return this.any { it.id == id && it.checkOutDateTime != null }
    }

    fun deleteRecord(id: String, checkIn: LocalDateTime): Boolean {
        return this.removeIf { it.id == id && it.checkInDateTime == checkIn }
    }

    fun checkOut(id: String, checkoutTime: LocalDateTime): Boolean {
        val record = this.find { it.id == id && it.checkOutDateTime == null }
        return record?.checkOut(checkoutTime) ?: false
    }

    fun summary(id: String): String {
        val total = this
            .filter { it.id == id && it.checkOutDateTime != null }
            .map { Duration.between(it.checkInDateTime, it.checkOutDateTime) }
            .fold(Duration.ZERO) { acc, d -> acc + d } //setter and updater

        return String.format(
            "%02d:%02d:%02d",
            total.toHours(),
            total.toMinutesPart(),
            total.toSecondsPart()
        )
    }

    override fun toString(): String {
        return if (this.isEmpty()) "No attendance records." else this.joinToString("\n")
    }
}
