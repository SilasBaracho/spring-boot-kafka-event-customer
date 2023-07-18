package sb.uma.useCase.customer.createCustomer

import org.springframework.stereotype.Service
import sb.uma.entity.CustomerEntity
import sb.uma.integration.producerEventCreateCustomer.response.Event
import sb.uma.resource.customer.CustomerOutput
import sb.uma.service.CustomerService
import sb.uma.useCase.customer.createCustomer.validation.CreateCustomerValidation
import sb.uma.util.OtpUtil

@Service
class CreateCustomerUseCase(
    val validation: List<CreateCustomerValidation>,
    val customerService: CustomerService,
    val otpUtil: OtpUtil
) {

    operator fun invoke(input: CreateCustomerInput): Event {
        validation.forEach{ it.validate(input) }

        val customer = CustomerEntity(
            email = input.email,
            passwd = input.password,
            otp = otpUtil.generateOtp()
        )

        val customerSaved = customerService.save(customer)

        val customerOutput = CustomerOutput(customerSaved)

        return sendMessage(customerOutput)
    }

    fun sendMessage(customerOutput: CustomerOutput): Event {
        return customerService.sendMessage(customerOutput)
    }
}