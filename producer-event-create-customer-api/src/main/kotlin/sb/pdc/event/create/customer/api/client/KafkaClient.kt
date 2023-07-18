package sb.pdc.event.create.customer.api.client

import kafka.event.create.customer.EventCreateCustomer
import org.springframework.http.HttpStatus
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.support.MessageBuilder
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

import org.springframework.util.concurrent.ListenableFuture
import sb.pdc.event.create.customer.api.resource.EventOutput
import sb.pdc.event.create.customer.api.useCase.event.customer.create.CreateCustomerEventInput
import java.net.http.HttpClient
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Component
class KafkaClient(
    private val kafkaTemplate: KafkaTemplate<String, EventCreateCustomer>
) {

    @Value("\${spring.kafka.topics.topic-name}")
    private lateinit var topic: String

    fun sendMessage(input: CreateCustomerEventInput): EventOutput {
        val message = eventCreateCustomerConverter(input)

        message?.idtCustomer.let {
            kafkaTemplate.send(topic, message)
            println(message)
        }

        return EventOutput(
            message = "Message sent with successfully",
            status = HttpStatus.OK.value()
        )
    }

    private fun eventCreateCustomerConverter(payload: CreateCustomerEventInput): EventCreateCustomer? {
        return EventCreateCustomer.newBuilder()
            .setIdtCustomer(payload.idtCustomer.toString())
            .setEmail(payload.email)
            .setOtp(payload.otp)
            .setFlgActive(payload.flgActive)
            .setFlgConfirmed(payload.flgConfirmed)
            .build()

    }

    /*private fun createMessageWithHeaders(idtCustomer: String, message: EventCreateCustomer, topic: String): Message<EventCreateCustomer> {
        return MessageBuilder.withPayload(message)
            .setHeader("hash", message.hashCode())
            .setHeader("version", "1.0.0")
            .setHeader("endOfLife", LocalDate.now().plusDays(1L))
            .setHeader("type", "fct")
            .setHeader("cid", idtCustomer)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.KEY, idtCustomer)
            .build()
    }*/



    /*fun sendMessage(key: String, topic: String): SendResult? {
        return try {
            val record: ProducerRecord<String, EventCreateCustomer> = ProducerRecord<K, V>(topic, key, outboundEvent)
            val result: SendResult = kafkaTemplate.send(record).get() as SendResult
            val metadata: RecordMetadata = result.getRecordMetadata()
            /*log.debug(
               String.format(
                    "Sent record(key=%s value=%s) meta(topic=%s, partition=%d, offset=%d)",
                    record.key(), record.value(), metadata.topic(), metadata.partition(), metadata.offset()
                )
            )*/
            result
        } catch (e: Exception) {
            val message = "Error sending message to topic $topic"
            //log.error(message)
            throw RuntimeException(message, e)
        }
    }*/
}