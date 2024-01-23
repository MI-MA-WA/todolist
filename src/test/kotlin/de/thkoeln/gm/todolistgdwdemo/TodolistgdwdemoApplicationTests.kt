package de.thkoeln.gm.todolistgdwdemo

import de.thkoeln.gm.todolistgdwdemo.tasks.TasksService
import de.thkoeln.gm.todolistgdwdemo.users.UsersService
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.http.server.reactive.MockServerHttpRequest.post
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class TodolistgdwdemoApplicationTests (
) {
    @Autowired lateinit var mockMvc: MockMvc

    @Test
    fun `should return start page`(){
        mockMvc.get("/").andDo { print() }
            .andExpect {
                status {isOk()}
            }
    }

}
