package sb.uma.useCase.customer.verifyAccount

data class VerifyAccountInput(
    val email: String,
    val otp: String
): java.io.Serializable{}
