package de.thkoeln.gm.todolistgdwdemo.tasks

import de.thkoeln.gm.todolistgdwdemo.users.User
import de.thkoeln.gm.todolistgdwdemo.users.UsersService
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Controller
class TasksController (private val tasksService: TasksService, private val usersService: UsersService) {

    @PostMapping("/users/{userId}/tasks")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun saveTask(name: String, @PathVariable userId: UUID): String {
        val user: User? = usersService.findById(userId)
        if(user != null) {
            var task: Task = Task()
            task.name = name
            task.user = user
            tasksService.save(task)
            return task.toString()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/users/{userId}/tasks")
    @ResponseBody
    fun getAllTasks(@PathVariable userId: UUID): String {
        val user: User? = usersService.findById(userId)
        if(user != null) {
            val tasks: List<Task> = tasksService.getAllOpenByUser(user)
            return tasks.joinToString(";")
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    }

    @GetMapping("/users/{userId}/tasks/{id}")
    @ResponseBody
    fun getTask(@PathVariable userId: UUID, @PathVariable id: UUID): String {
        val task = tasksService.findById(id)
        val user: User? = usersService.findById(userId)

        if(task != null && user != null){

            if(user.id != task.user?.id) {
                throw ResponseStatusException(HttpStatus.FORBIDDEN)
            }

            return task.toString()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/users/{userId}/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateTask(@PathVariable userId: UUID, @PathVariable id: UUID, open: Boolean?, name: String?){
        var task: Task? = tasksService.findById(id)
        var user: User? = usersService.findById(userId)

        if (task != null && user != null) {

            // Changing tasks of another user is not allowed
            if(user.id != task.user?.id) {
                throw ResponseStatusException(HttpStatus.FORBIDDEN)
            }

            if (open != null) {
                task.open = open
            }

            if (name != null){
                task.name = name
            }

            tasksService.save(task)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    }

    @DeleteMapping("/users/{userId}/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable userId: UUID, @PathVariable id: UUID){

        var task = tasksService.findById(id)
        var user: User? = usersService.findById(userId)

        if(task != null && user != null){
            if(user.id != task.user?.id) {
                throw ResponseStatusException(HttpStatus.FORBIDDEN)
            }

            tasksService.delete(task)
        }
    }

    @DeleteMapping("/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllTasks(@PathVariable userId: UUID){

        var user: User? = usersService.findById(userId)
        if(user != null) {
            for (task in user.tasks){
                tasksService.delete(task)
            }
        }

    }
}