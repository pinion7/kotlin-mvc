package hello.kotlinmvc.controller;

import hello.kotlinmvc.model.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseController {

    @GetMapping
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {

        /*
        if (age == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락되었습니다.");
        }

        if (age < 20) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값은 20보다 커야 합니다.");
        }

        return ResponseEntity.ok("Ok");
         */

        return age?.let {
            // age not null
            if (age < 20) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값은 20보다 커야 합니다.");
            }
            ResponseEntity.ok("OK");
        }?: kotlin.run {
            // age is null
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락되었습니다.");
        }
    }

    @PostMapping
    fun postMapping(@RequestBody body: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @PutMapping
    fun putMapping(@RequestBody body: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
