package sb.uma.integration.producerEventCreateCustomer

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import sb.uma.integration.producerEventCreateCustomer.response.Event
import sb.uma.resource.customer.CustomerOutput


@FeignClient(
    url = "\${feign.integration.producer-event-create-customer}",
    name = "producer-event-create-customer"
)
@Component
interface ProducerEventCreateCustomerApiClient {
    @PostMapping("/v1/event/publish")
    fun sendMessage(@RequestBody payload: CustomerOutput): Event
}

