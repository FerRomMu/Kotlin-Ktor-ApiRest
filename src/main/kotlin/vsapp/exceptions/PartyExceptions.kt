package vsapp.exceptions

class ForbiddenPartyException(): Exception("Invalid party for this user.")
class MalformedPartyException(): Exception("Given party it's invalid.")
