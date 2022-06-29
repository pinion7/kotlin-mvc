package hello.kotlinmvc.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import hello.kotlinmvc.annotation.StringFormatDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

@JsonNaming(SnakeCaseStrategy::class)
data class UserRequest(
    @field:NotEmpty // 값이 없는지 검증
    @field:Size(min = 2, max = 5)
    var name: String? = null,

    @field:PositiveOrZero
    var age: Int? = null,

    @field:Email
    var email: String? = null,

    @field:NotBlank // 빈문자열인지 검증
    var address: String? = null,

//    @JsonProperty("phone_number")
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
    var phoneNumber: String? = null,

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createdAt: String? = null
) {

//    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다.")
//    private fun isValidCreatedAt(): Boolean {
//        return try {
//            println(this.createdAt)
//            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            true;
//        } catch (e: Exception) {
//            false;
//        }
//    }
}

//{
//    "result": {
//        "result_code": "OK",
//        "result_message": "성공",
//    }
//    "description": "~~~~",
//    "user": [
//        {
//            "name": "a",
//            "age": 10,
//            "email": "",
//            "phoneNumber": ""
//        },
//        {
//            "name": "b",
//            "age": 20,
//            "email": "",
//            "phoneNumber": ""
//        },
//        {
//            "name": "c",
//            "age": 30,
//            "email": "",
//            "phoneNumber": ""
//        }
//    ]
//}