package sb.csm.event.create.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ConsumerEventCreateCustomerApplication

fun main(args: Array<String>) {
	runApplication<ConsumerEventCreateCustomerApplication>(*args)
}
