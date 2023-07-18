package sb.uma.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sb.uma.entity.CustomerEntity
import java.util.Optional
import java.util.UUID

@Repository
interface CustomerRepository: JpaRepository<CustomerEntity, UUID> {

    fun findByEmail(email: String): Optional<CustomerEntity>
}