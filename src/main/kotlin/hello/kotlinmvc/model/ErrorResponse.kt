package hello.kotlinmvc.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(SnakeCaseStrategy::class)
data class ErrorResponse(
    var error: String? = null,
    var statusCode: Int? = null,
    var httpMethod: String? = null,
    var message: String? = null,
    var path: String? = null,
    var timestamp: LocalDateTime? = null,
    var validation: MutableList<Validation>? = mutableListOf()
)

data class Validation(
    var field: String? = null,
    var message: String? = null,
    var value: Any? = null
)