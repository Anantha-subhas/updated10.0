enum class Department(val displayName: String) {
    CSE("Computer Science and Engineering"),
    IT("Information Technology"),
    ECE("Electronics and Communication Engineering"),
    EEE("Electrical and Electronics Engineering");

    override fun toString(): String = displayName
}
