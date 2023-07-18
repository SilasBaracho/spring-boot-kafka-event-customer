package sb.ema.resource

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sb.ema.entity.EmailEntity
import sb.ema.useCase.email.send.SendEmailInput
import sb.ema.useCase.email.send.SendEmailUseCase

@RestController
@RequestMapping("v1/email")
class EmailResource(
    val sendEmailUseCase: SendEmailUseCase
) {

    @PostMapping("/send")
    fun sendEmail(@RequestBody @Valid input: SendEmailInput): ResponseEntity<EmailEntity> {
        return ResponseEntity<EmailEntity>(sendEmailUseCase.invoke(input), HttpStatus.CREATED)
    }

}