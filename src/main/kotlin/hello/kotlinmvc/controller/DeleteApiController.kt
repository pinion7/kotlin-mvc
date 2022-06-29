package hello.kotlinmvc.controller

import com.sun.istack.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam name: String,

        @RequestParam
        @NotNull
        @Min(value = 20, message = "age는 20보다 커야 합니다.")
        age: Int
    ): String {
        println(name)
        println(age)
        return "$name $age";
    }

    @RequestMapping(method = [RequestMethod.DELETE], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping";
    }

    @DeleteMapping("/delete-mapping/{name}/{age}")
    fun deleteMappingPath(
        @PathVariable
        @Size(min = 2, max = 5)
        @NotNull
        name: String,

        @PathVariable age: Int
    ): String {
        return "$name $age"
    }
}