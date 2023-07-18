package sb.csm.event.create.customer.config

import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sb.uma.exception.FeignErrorDecoderException


@Configuration
class FeignConfig {

    @Bean
    fun errorDecoder(): ErrorDecoder? {
        return FeignErrorDecoderException()
    }
}