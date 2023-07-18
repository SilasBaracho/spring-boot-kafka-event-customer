package sb.pdc.event.create.customer.api.config

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import kafka.event.create.customer.EventCreateCustomer
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfig(
    val kafkaProperties: KafkaProperties
) {

    @Value("\${spring.kafka.topics.topic-name}")
    private lateinit var topic: String

    @Value("\${spring.kafka.properties.schema.registry.url}")
    private lateinit var schemaRegistryUrl: String

    @Bean
    fun producerFactory(): ProducerFactory<String, EventCreateCustomer> {
        val configs = HashMap<String, Any>()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = kafkaProperties.producer.keySerializer
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = kafkaProperties.producer.valueSerializer
        configs["schema.registry.url"] = schemaRegistryUrl
        return DefaultKafkaProducerFactory(configs)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, EventCreateCustomer> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs = HashMap<String, Any>()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        return KafkaAdmin(configs)
    }

    @Bean
    fun create(): NewTopic {
        return NewTopic(topic, 3, "1".toShort())
    }

}