package vsapp.exceptions

class ConflictMailOrUserException(private val msg: String): Exception("Username or email already in use.")