package sb.ema.exception.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class StandardError<T>(
    @JsonProperty("status")
    val status: Int,
    @JsonProperty("error")
    val error: T,
    @JsonProperty("message")
    val message: String,
    @JsonProperty("timeStamp")
    val path: String,
    @JsonProperty("path")
    val timeStamp: LocalDateTime = LocalDateTime.now()
) {
    override fun toString(): String {
        return "(status=$status, message='$message', error=$error, timeStamp=$timeStamp, path='$path')"
    }
}
