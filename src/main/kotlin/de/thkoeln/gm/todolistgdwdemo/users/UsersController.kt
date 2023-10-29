package de.thkoeln.gm.todolistgdwdemo.users

import de.thkoeln.gm.todolistgdwdemo.tasks.Task
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UsersController {
    @GetMapping("/users")
    @ResponseBody
    fun saveUser(email: String, taskName: String): String{
        var user: User = User()
        user.email = email
        var  task : Task = Task()
        task.name = taskName
        user.tasks.add(task)
        return user.toString()
    }
}