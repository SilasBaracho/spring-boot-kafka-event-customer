package sb.pdc.event.create.customer.api.resource

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sb.pdc.event.create.customer.api.useCase.event.customer.create.CreateCustomerEventInput
import sb.pdc.event.create.customer.api.useCase.event.customer.create.CreateCustomerEventUseCase

@RestController
@RequestMapping("v1/event")
class EventResource(
    val createCustomerEventUseCase: CreateCustomerEventUseCase
) {

    @PostMapping("/publish")
    fun sendMessage(@RequestBody input: CreateCustomerEventInput): EventOutput {
        return createCustomerEventUseCase.invoke(input)
        //return ResponseEntity.ok("Message sent")
    }
}