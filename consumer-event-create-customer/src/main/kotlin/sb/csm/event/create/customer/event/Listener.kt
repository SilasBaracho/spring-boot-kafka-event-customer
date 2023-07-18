package sb.csm.event.create.customer.event

import kafka.event.create.customer.EventCreateCustomer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import sb.csm.event.create.customer.integration.emailManagerApi.EmailManagerApi
import sb.csm.event.create.customer.integration.emailManagerApi.request.EmailManagerApiRequest

@Component
class Listener(
    val emailApi: EmailManagerApi
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["\${spring.kafka.topics.topic-name}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listen(@Payload data: ConsumerRecord<String, EventCreateCustomer>){
        logger.info("Thread: {}", Thread.currentThread().id);
        logger.info("Received: {}", data);
        logger.info("Message: {}", data.value());
        val response = emailApi.sendEmail(EmailManagerApiRequest(data.value()))
        logger.info("SendEmail: {}", response)
    }
}