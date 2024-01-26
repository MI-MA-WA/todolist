package de.thkoeln.gm.todolistgdwdemo.users

import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class UsersRestControllerTests() {
    @Autowired lateinit var mockMvc: MockMvc
    @Test
    fun `should add a new user` (){
        val userJSON = JSONObject()
        userJSON.put("email","viet.nguyen@th-koeln.de")

        mockMvc.post("/api/v1/users") {
            contentType = MediaType.APPLICATION_JSON
            content = userJSON.toString()
            accept = MediaType.APPLICATION_JSON
        }.andDo { print() }
            .andExpect {
                status {isCreated()}
                content {jsonPath("$.email") {value ("viet.nguyen@th-koeln.de")}}
            }
    }

}