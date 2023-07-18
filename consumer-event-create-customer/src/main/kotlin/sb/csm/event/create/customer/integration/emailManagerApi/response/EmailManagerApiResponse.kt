package sb.csm.event.create.customer.integration.emailManagerApi.response

import java.time.LocalDateTime

data class EmailManagerApiResponse(
    val id: String,
    val ownerRef: String,
    val emailFrom: String,
    val emailTo: String,
    val subject: String,
    val text: String,
    val sendDateEmail: LocalDateTime,
    var statusEmail: String
): java.io.Serializable{}
