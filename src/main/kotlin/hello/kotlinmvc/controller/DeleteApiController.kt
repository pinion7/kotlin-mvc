package hello.kotlinmvc.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(@RequestParam name: String, @RequestParam age: Int): String {
        println(name)
        println(age)
        return "$name $age";
    }

    @RequestMapping(method = [RequestMethod.DELETE], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping";
    }

    @DeleteMapping("/delete-mapping/{name}/{age}")
    fun deleteMappingPath(@PathVariable name: String, @PathVariable age: Int): String {
        return "$name $age"
    }
}