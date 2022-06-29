package hello.kotlinmvc.exception

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hello.kotlinmvc.model.UserRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
internal class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc;

    @Test
    fun helloTest() {
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect (
            status().isOk
        ).andExpect(
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>();
        queryParams.add("name", "steve");
        queryParams.add("age", "20");

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("steve 20")
        ).andDo(print());
    }

    @Test
    fun getTestFail() {
        val queryParams = LinkedMultiValueMap<String, String>();
        queryParams.add("name", "steve");
        queryParams.add("age", "9");

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.error").value("Bad Request")
        ).andExpect(
            jsonPath("\$.validation[0].value").value(9)
        ).andDo(print());
    }

    @Test
    fun postTest () {
        val userRequest = UserRequest().apply {
            this.name = "steve"
            this.age = 10
            this.phoneNumber = "010-1234-5678"
            this.address = "성남시"
            this.email = "steve@gmail.com"
            this.createdAt = "2020-10-10 13:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest);

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("steve")
        ).andExpect(
            jsonPath("\$.age").value(10)
        ).andExpect(
            jsonPath("\$.email").value("steve@gmail.com")
        ).andExpect(
            jsonPath("\$.address").value("성남시")
        ).andExpect(
            jsonPath("\$.phone_number").value("010-1234-5678")
        ).andDo(print())
    }

    @Test
    fun postTestFail () {
        val userRequest = UserRequest().apply {
            this.name = "steve"
            this.age = -1
            this.phoneNumber = "010-1234-5678"
            this.address = "성남시"
            this.email = "steve@gmail.com"
            this.createdAt = "2020-10-10 13:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest);

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.error").value("Bad Request")
        ).andExpect(
            jsonPath("\$.validation[0].value").value(-1)
        ).andDo(print())
    }

}