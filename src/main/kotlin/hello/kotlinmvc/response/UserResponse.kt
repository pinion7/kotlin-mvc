package hello.kotlinmvc.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import hello.kotlinmvc.model.UserRequest

data class UserResponse(
    var result: Result? = null,
    var description: String? = null,
    var user: MutableList<UserRequest>? = null
) {
}

@JsonNaming(SnakeCaseStrategy::class)
data class Result(
    var resultCode: String? = null,
    var resultMessage: String? = null
)