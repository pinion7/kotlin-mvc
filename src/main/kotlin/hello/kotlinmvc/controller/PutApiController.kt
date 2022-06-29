package hello.kotlinmvc.controller

import hello.kotlinmvc.model.UserRequest
import hello.kotlinmvc.response.Result
import hello.kotlinmvc.response.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping";
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping";
    }

    @PutMapping("/put-mapping/object")
    fun putMappingObject(@RequestBody body: UserRequest): UserResponse {
        return UserResponse().apply {
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            this.description = "~~~~~~~~~~"
        }.apply {
            val userList = mutableListOf<UserRequest>();
            userList.add(body);
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "a@gamil.com"
                this.address = "a address"
                this.phoneNumber =  "010-1111-0101"
            });
            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 20
                this.email = "b@gamil.com"
                this.address = "b address"
                this.phoneNumber =  "010-1211-0101"
            });
            this.user = userList;
        }
    }

}