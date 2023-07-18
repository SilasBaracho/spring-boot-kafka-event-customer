package sb.pdc.event.create.customer.api.useCase.event.customer.create

import java.util.UUID

data class CreateCustomerEventInput (
    val idtCustomer: UUID,
    val email: String,
    val otp: String,
    val flgActive: Boolean,
    val flgConfirmed: Boolean
): java.io.Serializable{}