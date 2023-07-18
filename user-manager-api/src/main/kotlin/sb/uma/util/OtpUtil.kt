package sb.uma.util

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class OtpUtil {

    fun generateOtp(): String {
        val randomNumber = Random.nextInt(1_000_000)
        return randomNumber.toString().padStart(6, '0')
    }
}