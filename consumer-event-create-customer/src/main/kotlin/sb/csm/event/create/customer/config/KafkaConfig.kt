package sb.csm.event.create.customer.config

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import kafka.event.create.customer.EventCreateCustomer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
@EnableKafka
class KafkaConfig(
    val kafkaProperties: KafkaProperties
) {
    @Value("\${spring.kafka.properties.schema.registry.url}")
    private lateinit var schemaRegistryUrl: String

    @Bean
    fun consumerFactory(): ConsumerFactory<String, EventCreateCustomer> {
        val configs = HashMap<String, Any>()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = kafkaProperties.consumer.keyDeserializer
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = kafkaProperties.consumer.valueDeserializer
        configs[KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG] = true
        configs["schema.registry.url"] = schemaRegistryUrl
        return DefaultKafkaConsumerFactory(configs)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, EventCreateCustomer> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, EventCreateCustomer>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

}