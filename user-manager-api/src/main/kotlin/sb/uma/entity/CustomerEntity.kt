package sb.uma.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "customer")
data class CustomerEntity(
    @Id
    @Column(name= "idt_customer")
    val id: UUID = UUID.randomUUID(),

    @Column(name= "email")
    val email: String,

    @Column(name= "password")
    val passwd: String,

    @Column(name= "otp")
    val otp: String,

    @Column(name = "flg_active")
    val flgActive: Boolean = true,

    @Column(name = "flg_confirmed")
    var flgConfirmed: Boolean = false,

    @Column(name= "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name= "updated_at")
    var updatedAt: LocalDateTime? = null,
): java.io.Serializable{

    fun flgConfirmed(status: Boolean) {
        flgConfirmed = status
    }

    fun updatedAt(date: LocalDateTime) {
        updatedAt = date
    }
}
