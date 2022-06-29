package hello.kotlinmvc.advice

import hello.kotlinmvc.exception.ExceptionApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [ExceptionApiController::class])
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e: RuntimeException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
    }


    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Index Error");
    }
}