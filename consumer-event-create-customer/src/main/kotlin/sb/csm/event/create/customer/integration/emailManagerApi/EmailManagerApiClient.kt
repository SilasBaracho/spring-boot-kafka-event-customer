package sb.csm.event.create.customer.integration.emailManagerApi

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import sb.csm.event.create.customer.integration.emailManagerApi.request.EmailManagerApiRequest
import sb.csm.event.create.customer.integration.emailManagerApi.response.EmailManagerApiResponse

@FeignClient(
    url = "\${feign.integration.email-manager-api}",
    name = "email-manager-api"
)
interface EmailManagerApiClient {

    @PostMapping("/v1/email/send")
    fun sendEmail(@RequestBody input: EmailManagerApiRequest): EmailManagerApiResponse

}