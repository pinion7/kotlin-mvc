package hello.kotlinmvc.exception

import hello.kotlinmvc.model.ErrorResponse
import hello.kotlinmvc.model.UserRequest
import hello.kotlinmvc.model.Validation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.constraints.*

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {
    @GetMapping("/hello")
    fun hello(): String {
        val list = mutableListOf<String>();
        return "hello";
//        if (true) {
//            throw RuntimeException("강제 exception 생성");
//        }
    }

    @GetMapping
    fun get(
        @RequestParam
        @NotBlank
        @Size(min = 2, max = 5)
        name: String,

        @RequestParam
        @Min(10)
        age: Int
    ): String {
        println(name)
        return "$name $age";
    }

    @PostMapping
    fun post(@RequestBody @Validated body: UserRequest): UserRequest {
        println(body);
        return body;
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNOtValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val validationErrors = mutableListOf<Validation>();

        e.bindingResult.allErrors.forEach { errorObject ->
            val validation = Validation().apply {
                val field = errorObject as FieldError;

                this.field = field.field;
                this.message = errorObject.defaultMessage;
                this.value = errorObject.rejectedValue;
            }
            validationErrors.add(validation);
        }

        val errorResponse = ErrorResponse().apply {
            this.error = "Bad Request";
            this.statusCode = HttpStatus.BAD_REQUEST.value();
            this.httpMethod = request.method;
            this.path = request.requestURI.toString();
            this.message = "잘못된 요청입니다.";
            this.timestamp = LocalDateTime.now();
            this.validation = validationErrors;
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    private fun constraintViolationException(e: ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val validationErrors = mutableListOf<Validation>();

        e.constraintViolations.forEach {
            val validation = Validation().apply {
                this.field = it.propertyPath.last().name;
                this.message = it.message;
                this.value = it.invalidValue;
            }
            validationErrors.add(validation);
        }

        val errorResponse = ErrorResponse().apply {
            this.error = "Bad Request";
            this.statusCode = HttpStatus.BAD_REQUEST.value();
            this.httpMethod = request.method;
            this.path = request.requestURI.toString();
            this.message = "잘못된 요청입니다.";
            this.timestamp = LocalDateTime.now();
            this.validation = validationErrors;
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 해당 컨트롤러 안에서만 예외를 잡아줌
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Index Error");
    }

//    {
//        "error": "Bad Request",
//        "statusCode": 400,
//        "message": "잘못된 요청입니다.",
//        "path": "/api/exception/hello",
//        "timestamp": "2020-10-30T13:00:00",
//        "validation": [
//            {
//                "field": "name",
//                "message": "5글자 이상이어야 합니다."
//            }
//        ]
//    }
}