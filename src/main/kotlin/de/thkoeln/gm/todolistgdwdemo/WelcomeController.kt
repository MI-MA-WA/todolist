package de.thkoeln.gm.todolistgdwdemo

import de.thkoeln.gm.todolistgdwdemo.tasks.Task
import de.thkoeln.gm.todolistgdwdemo.tasks.TaskForm
import de.thkoeln.gm.todolistgdwdemo.tasks.TasksService
import de.thkoeln.gm.todolistgdwdemo.users.User
import de.thkoeln.gm.todolistgdwdemo.users.UserForm
import de.thkoeln.gm.todolistgdwdemo.users.UsersService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Controller
class WelcomeController (private val usersService: UsersService, private val tasksService: TasksService) {
    @GetMapping("/")
    fun index(model: Model): String {
        val users: List<User> = usersService.getAllUsers()
        model["user"] = UserForm()
        model["users"] = users
        return "index"
    }

    @GetMapping("/users/{id}/tasksform")
    fun tasksForm(model: Model, @PathVariable id: UUID): String {
        val user: User? = usersService.findById(id)
        if(user != null) {
            val tasks: List<Task> = tasksService.getAllOpenByUser(user)
            model["tasks"] = tasks
            model["task"] = TaskForm()
            model["user"] = user
            return "tasksForm"
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/users")
    fun createUser(userForm: UserForm) : String{
        usersService.save(User(userForm.email))
        return "redirect:/"
    }

    @PostMapping("/users/{id}/tasks")
    fun createTask(taskForm: TaskForm, @PathVariable id: UUID) : String{
        println(taskForm.name)
        val user: User? = usersService.findById(id)
        if(user != null) {
            var task : Task = Task()
            task.name = taskForm.name
            task.user = user
            tasksService.save(task)
            return "redirect:/users/${user.id}/tasksform"
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}