package sb.ema.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sb.ema.entity.EmailEntity
import java.util.UUID

@Repository
interface EmailRepository: JpaRepository<EmailEntity, UUID> {}