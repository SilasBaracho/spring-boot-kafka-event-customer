package sb.uma.resource.customer

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import sb.uma.integration.producerEventCreateCustomer.response.Event
import sb.uma.useCase.customer.createCustomer.CreateCustomerInput
import sb.uma.useCase.customer.createCustomer.CreateCustomerUseCase
import sb.uma.useCase.customer.verifyAccount.VerifyAccountInput
import sb.uma.useCase.customer.verifyAccount.VerifyAccountUseCase

@RestController
@RequestMapping("v1/customer")
class CustomerResource(
    val createCustomerUseCase: CreateCustomerUseCase,
    val verifyAccountUseCase: VerifyAccountUseCase
) {

    @PostMapping("/create")
    fun createCustomer(@RequestBody @Valid input: CreateCustomerInput): Event {
        return createCustomerUseCase.invoke(input)
    }

    @PutMapping("/verify-account")
    fun accountConfirmation(@RequestParam email: String, @RequestParam otp: String) {
        return verifyAccountUseCase.invoke(VerifyAccountInput(email, otp))
    }
}