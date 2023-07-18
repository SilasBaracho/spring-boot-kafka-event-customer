package sb.uma.useCase.customer.verifyAccount.validation

import org.springframework.stereotype.Component
import sb.uma.exception.NotFoundException
import sb.uma.service.CustomerService
import sb.uma.useCase.customer.verifyAccount.VerifyAccountInput

@Component
class VerifyAccountEmailExists(val customerService: CustomerService): VerifyAccountValidation {
    override fun validate(input: VerifyAccountInput) {
        customerService.findByEmail(input.email).orElseThrow{
            throw NotFoundException("User not found with this email: $input.email")
        }
    }
}