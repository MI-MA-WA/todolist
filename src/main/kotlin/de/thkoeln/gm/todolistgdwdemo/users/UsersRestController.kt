package de.thkoeln.gm.todolistgdwdemo.users

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class UsersRestController (private val usersService: UsersService) {


    @PostMapping("/users")
    fun saveUser(email: String){
        val user = User()
        user.email = email
        usersService.save(user)
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
}