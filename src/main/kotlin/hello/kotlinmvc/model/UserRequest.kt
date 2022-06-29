package hello.kotlinmvc.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class UserRequest(
    var name: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var address: String? = null,

//    @JsonProperty("phone_number")
    var phoneNumber: String? = null
) {
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