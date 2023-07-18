package sb.uma.useCase.customer.createCustomer.validation

import org.springframework.stereotype.Component
import sb.uma.exception.BadRequestException
import sb.uma.service.CustomerService
import sb.uma.useCase.customer.createCustomer.CreateCustomerInput

@Component
class CreateCustomerRegisteredEmail(val customerService: CustomerService): CreateCustomerValidation {

    override fun validate(input: CreateCustomerInput) {
        val emailExists = customerService.findByEmail(input.email)
        if (emailExists.isPresent) {
            throw BadRequestException("Registered email, use another email or recover yours.")
        }
    }
}