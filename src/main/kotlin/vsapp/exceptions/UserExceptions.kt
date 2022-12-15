package vsapp.exceptions

class WrongLoginException(private val msg: String): Exception(msg)