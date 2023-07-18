package sb.pdc.event.create.customer.api.useCase.event.customer.create

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import sb.pdc.event.create.customer.api.client.KafkaClient
import sb.pdc.event.create.customer.api.resource.EventOutput

@Service
class CreateCustomerEventUseCase(
    val kafkaClient: KafkaClient
) {

    @Value("\${spring.kafka.topics.topic-name}")
    private lateinit var topicName: String

    operator fun invoke(input: CreateCustomerEventInput): EventOutput {
        return kafkaClient.sendMessage(input)
    }
}