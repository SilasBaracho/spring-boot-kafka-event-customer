package sb.ema.service

import jakarta.mail.internet.MimeMessage
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import sb.ema.entity.EmailEntity
import sb.ema.entity.enum.StatusEmail
import sb.ema.repository.EmailRepository


@Service
class EmailService(
    val emailRepository: EmailRepository,
    val emailSender: JavaMailSender
) {

    fun save(entity: EmailEntity): EmailEntity {
        return emailRepository.save(entity)
    }

    fun sendEmail(email: EmailEntity): EmailEntity {
        try {
            val message: MimeMessage = emailSender.createMimeMessage()
            val helper: MimeMessageHelper = MimeMessageHelper(message, true)
            helper.setSubject(email.subject)
            helper.setFrom(email.emailFrom)
            helper.setTo(email.emailTo)
            helper.setText(email.text, true)
            emailSender.send(message)

            email.statusEmail(StatusEmail.SENT)
        }catch (e: MailException){
            email.statusEmail(StatusEmail.ERROR)
        }finally {
            return this.save(email)
        }
    }
}