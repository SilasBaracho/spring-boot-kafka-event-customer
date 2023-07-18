package sb.uma.useCase.customer.verifyAccount

import org.springframework.stereotype.Service
import sb.uma.service.CustomerService
import sb.uma.useCase.customer.verifyAccount.validation.VerifyAccountValidation
import java.time.Duration
import java.time.LocalDateTime

@Service
class VerifyAccountUseCase(
    val validation: List<VerifyAccountValidation>,
    val customerService: CustomerService,
) {

    operator fun invoke(input: VerifyAccountInput){
        validation.forEach{ it.validate(input) }

        val customer = customerService.findByEmail(input.email).get()

        if (customer.email == input.email && customer.otp == input.otp) {
            customer.flgConfirmed(true)
            customer.updatedAt(LocalDateTime.now())
            customerService.save(customer)
        }
    }
}