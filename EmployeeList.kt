class EmployeeList : ArrayList<Employee>() {

    fun addEmployee(employee: Employee): Boolean {
        // if same id exists
        return if (this.any { it.id == employee.id }) false else this.add(employee)
    }

    fun deleteEmployee(id: String): Boolean {
        return this.removeIf { it.id == id }
    }

    fun exists(id: String): Boolean {
        return this.any { it.id == id }
    }

    fun getEmployee(id: String): Employee? = this.find { it.id == id }

    override fun toString(): String {
        return if (this.isEmpty()) "No employees found." else this.joinToString("\n")
    }
}
