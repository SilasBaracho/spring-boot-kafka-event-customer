package sb.uma.integration.producerEventCreateCustomer

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import sb.uma.integration.producerEventCreateCustomer.response.Event
import sb.uma.resource.customer.CustomerOutput

@Component
class ProducerEventCreateCustomerApi(
    val producerClient: ProducerEventCreateCustomerApiClient
) {

    fun sendMessage(payload: CustomerOutput): Event {
        return producerClient.sendMessage(payload)
    }
}