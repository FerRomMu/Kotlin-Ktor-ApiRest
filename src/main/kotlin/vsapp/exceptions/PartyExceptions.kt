package vsapp.exceptions

class ForbiddenPartyException(private val msg: String): Exception(msg)
class NotFoundPartyException(private val msg: String): Exception(msg)
