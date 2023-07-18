package sb.ema.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import sb.ema.exception.model.StandardError

private const val VALIDATION_ERROR = "Validation error"

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError<Map<String, List<String?>>>> {

        val errors = e.fieldErrors.groupBy(FieldError::getField, FieldError::getDefaultMessage)

        return ResponseEntity.badRequest().body(
            StandardError(
                status = HttpStatus.BAD_REQUEST.value(),
                message = VALIDATION_ERROR,
                error = errors,
                path = request.requestURI
            )
        )
    }
}