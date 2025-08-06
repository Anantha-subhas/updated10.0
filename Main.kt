fun main() {
    val employeeManager = EmployeeManager()

    while (true) {
        println(
            """
            === Employee Attendance System ===
            1. Add Employee
            2. Show All Employees
            3. Check-In
            4. Check-Out
            5. View Attendance Records
            6. View Total Working Summary
            7. Delete an Employee
            8. Exit
        """.trimIndent()
        )

        print("Choose an option: ")
        when (readLine()?.trim()) {
            "1" -> {
                try {
                    print("Enter First Name: ")
                    val firstName = readLine()!!.trim()

                    print("Enter Last Name: ")
                    val lastName = readLine()!!.trim()

                    println("Select Role:")
                    Role.entries.forEachIndexed { index, role -> println("${index + 1}. $role") }
                    val roleIndex = readLine()?.toIntOrNull()?.minus(1) ?: -1
                    val selectedRole = Role.entries.getOrNull(roleIndex)
                        ?: throw IllegalArgumentException("Invalid role selection.")

                    println("Select Supervisor:")
                    SupervisorRole.entries.forEachIndexed { index, supervisor -> println("${index + 1}. $supervisor") }
                    val supervisorIndex = readLine()?.toIntOrNull()?.minus(1) ?: -1
                    val selectedSupervisor = SupervisorRole.entries.getOrNull(supervisorIndex)
                        ?: throw IllegalArgumentException("Invalid supervisor selection.")

                    println("Select Department:")
                    Department.entries.forEachIndexed { index, department -> println("${index + 1}. $department") }
                    val departmentIndex = readLine()?.toIntOrNull()?.minus(1) ?: -1
                    val selectedDepartment = Department.entries.getOrNull(departmentIndex)
                        ?: throw IllegalArgumentException("Invalid department selection.")

                    val newEmployee = Employee(firstName, lastName, selectedRole, selectedSupervisor, selectedDepartment)
                    println("Generated Employee ID: ${newEmployee.id}")
                    val added = employeeManager.addEmployee(newEmployee)
                    println(if (added) "Employee added successfully!" else "Employee ID already exists.")
                } catch (e: Exception) {
                    println("Invalid input. Please try again.")
                }
            }

            "2" -> println(employeeManager.getAllEmployees())

            "3" -> {
                print("Enter Employee ID for Check-In: ")
                val employeeId = readLine()?.trim().orEmpty()
                print("Enter check-in date (yyyy-MM-dd) or press Enter for current: ")
                val checkInStr = readLine()!!.trim()
                val status = employeeManager.checkIn(employeeId, checkInStr)
                println(status)
            }

            "4" -> {
                print("Enter Employee ID for Check-Out: ")
                val employeeId = readLine()?.trim().orEmpty()
                print("Enter check-out date (yyyy-MM-dd HH:mm) or press Enter for current: ")
                val checkOutStr = readLine()!!.trim()
                val status = employeeManager.checkOut(employeeId, checkOutStr)
                println(status)
            }

            "5" -> println(employeeManager.viewAttendance())

            "6" -> println(employeeManager.viewSummary())
             "7" -> {
                 println("enter employee id to delete")
                 val id = readLine()!!.trim()
                 val deleted = employeeManager.deleteEmployee(id)
                 println(if(deleted) "Employee deleted " else "no employee found with ID $id")
             }
            "8" -> {
                println("Exiting...!")
                return
            }

            else -> println("Invalid option. Please select a number from 1 to 7.")
        }
    }
}
