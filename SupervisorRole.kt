enum class SupervisorRole(val displayName: String) {
    MANAGER("Manager"),
    CEO("CEO"),
    COO("Chef operating officer");
    override fun toString(): String = displayName
}