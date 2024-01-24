package de.thkoeln.gm.todolistgdwdemo.users

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/v1/")
class UsersRestController (private val usersService: UsersService) {


    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUser(@RequestBody user: User, response: HttpServletResponse) : User {
        usersService.save(user)
        //response.setHeader("Location","/users/${user.id}")
        return user
    }

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: UUID): User {
        val user: User? = usersService.findById(id)
        if(user != null){
            return user
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: UUID) {
        val user: User? = usersService.findById(id)
        if(user != null) {
            usersService.delete(user)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}