package sb.csm.event.create.customer.integration.emailManagerApi

import org.springframework.stereotype.Component
import sb.csm.event.create.customer.integration.emailManagerApi.request.EmailManagerApiRequest
import sb.csm.event.create.customer.integration.emailManagerApi.response.EmailManagerApiResponse

@Component
class EmailManagerApi(
    val emailApi: EmailManagerApiClient
) {

    fun sendEmail(input: EmailManagerApiRequest): EmailManagerApiResponse {
        return emailApi.sendEmail(input)
    }
}