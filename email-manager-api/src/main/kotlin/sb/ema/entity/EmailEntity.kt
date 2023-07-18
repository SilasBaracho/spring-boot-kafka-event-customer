package sb.ema.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import sb.ema.entity.enum.StatusEmail
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name= "email")
data class EmailEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    val ownerRef: String,

    val emailFrom: String,

    val emailTo: String,

    val subject: String,

    @Column(columnDefinition = "TEXT")
    val text: String,

    val sendDateEmail: LocalDateTime = LocalDateTime.now(),

    var statusEmail: StatusEmail? = null

): java.io.Serializable{

    fun statusEmail(status: StatusEmail) {
        statusEmail = status
    }
}
