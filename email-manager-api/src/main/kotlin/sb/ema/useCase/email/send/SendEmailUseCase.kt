package sb.ema.useCase.email.send

import org.springframework.stereotype.Service
import sb.ema.entity.EmailEntity
import sb.ema.service.EmailService

@Service
class SendEmailUseCase(
    val emailService: EmailService
) {

    operator fun invoke(input: SendEmailInput): EmailEntity {
        val email = EmailEntity(
            ownerRef = input.ownerRef,
            emailFrom = input.emailFrom,
            emailTo = input.emailTo,
            subject = input.subject,
            text = input.text
        )

        return emailService.sendEmail(email)
    }
}