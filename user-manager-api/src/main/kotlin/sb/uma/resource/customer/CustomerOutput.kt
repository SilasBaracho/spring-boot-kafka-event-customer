package sb.uma.resource.customer

import sb.uma.entity.CustomerEntity
import java.util.UUID

data class CustomerOutput(
    val idtCustomer: UUID,
    val email: String,
    val otp: String,
    val flgActive: Boolean = true,
    val flgConfirmed: Boolean = false,
): java.io.Serializable{

    constructor(output: CustomerEntity) : this(
        idtCustomer = output.id,
        email = output.email,
        otp = output.otp,
        flgActive = output.flgActive,
        flgConfirmed = output.flgConfirmed
    )
}
