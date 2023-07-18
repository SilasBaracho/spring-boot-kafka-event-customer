package sb.ema.useCase.email.send

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SendEmailInput(
    @field:NotBlank(message = "The ownerRef must be informed")
    @JsonProperty(value = "ownerRef")
    val ownerRef: String,

    @field:Email(message = "Invalid sender email")
    @JsonProperty(value = "emailFrom")
    val emailFrom: String,

    @field:Email(message = "Invalid recipient email")
    @JsonProperty(value = "emailTo")
    val emailTo: String,

    @field:NotBlank(message = "The Subject of the email must be informed")
    @JsonProperty(value = "subject")
    val subject: String,

    @field:NotBlank(message = "The email message must be informed")
    @JsonProperty(value = "text")
    val text: String,
): java.io.Serializable{}