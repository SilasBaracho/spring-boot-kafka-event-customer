package sb.uma.useCase.customer.createCustomer

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

data class CreateCustomerInput(
    @field:Email(message = "Invalid email")
    @JsonProperty(value = "email")
    val email: String,

    /*@field:Pattern(
        regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d.*\\d.*\\d).{9,}$",
        message = "A senha deve conter 2 letras maiúsculas, 1 letra minúscula e 4 dígitos numéricos."
    )*/
    @JsonProperty(value = "password")
    val password: String
): java.io.Serializable{}