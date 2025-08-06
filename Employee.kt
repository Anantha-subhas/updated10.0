class Employee(
    var firstName: String,
    var lastName: String,
    var role: Role,
    var reportTo: SupervisorRole,
    var department: Department
) {
    val id: String = generateId()
    init {
        require(firstName.isNotBlank()) { "First name cannot be empty." }
        require(lastName.isNotBlank()) { "Last name cannot be empty." }
    }

    companion object {
        private var counter = 1
        private fun generateId(): String = "E" + counter++.toString().padStart(3, '0')
    }

    override fun toString(): String {
        return "ID: $id | Name: $firstName $lastName | Role: $role | Reports To: $reportTo | Department: $department"
    }
}
