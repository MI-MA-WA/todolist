package de.thkoeln.gm.todolistgdwdemo.users

import de.thkoeln.gm.todolistgdwdemo.tasks.Task
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Controller
class UsersController (private val usersService: UsersService) {
    @PostMapping("/users")
    @ResponseBody
    fun saveUser(email: String): String{
        val user = User()
        user.email = email
        usersService.save(user)
        return user.toString()
    }

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: UUID): String {
        val user: User? = usersService.findById(id)
        if(user != null){
            return user.toString()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    }
}