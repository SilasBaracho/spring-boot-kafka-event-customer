package sb.uma.service

import org.springframework.stereotype.Service
import sb.uma.entity.CustomerEntity
import sb.uma.integration.producerEventCreateCustomer.ProducerEventCreateCustomerApi
import sb.uma.integration.producerEventCreateCustomer.response.Event
import sb.uma.repository.CustomerRepository
import sb.uma.resource.customer.CustomerOutput
import java.util.*

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val producerEvent: ProducerEventCreateCustomerApi
) {

    fun save(entity: CustomerEntity): CustomerEntity {
        return customerRepository.save(entity)
    }

    fun findByEmail(email: String): Optional<CustomerEntity> {
        return customerRepository.findByEmail(email)
    }

    fun sendMessage(entity: CustomerOutput): Event {
        return producerEvent.sendMessage(entity)
    }
}