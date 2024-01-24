package de.thkoeln.gm.todolistgdwdemo.tasks

import de.thkoeln.gm.todolistgdwdemo.users.User
import de.thkoeln.gm.todolistgdwdemo.users.UsersService
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/v1")
class TasksRestController(private val usersService: UsersService, private val tasksService: TasksService )
{

    @PostMapping("/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveTask(task: Task, @PathVariable userId: UUID): Task {
        val user: User? = usersService.findById(userId)
        if(user != null) {
            task.user = user
            tasksService.save(task)
            return task
        } else {
            throw ChangeSetPersister.NotFoundException()
        }
    }

    @GetMapping("/users/{userId}/tasks")
    fun getAllTasks(@PathVariable userId: UUID): List<Task> {
        val user: User? = usersService.findById(userId)
        if(user != null) {
            return tasksService.getAllOpenByUser(user)
        } else {
            throw ChangeSetPersister.NotFoundException()
        }

    }

    @GetMapping("/users/{userId}/tasks/{id}")
    fun getTask(@PathVariable userId: UUID, @PathVariable id: UUID): Task {
        var task = tasksService.findById(id)
        var user: User? = usersService.findById(userId)

        if(task != null && user != null){

            if(user.id != task.user?.id) {
                throw ResponseStatusException(HttpStatus.FORBIDDEN)
            }

            return task
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/users/{userId}/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateTask(@PathVariable userId: UUID, @PathVariable id: UUID, task: Task){
        var user: User? = usersService.findById(userId)

        if (tasksService.findById(id) != null && user != null) {

            // Changing tasks of another user is not allowed
            if(user.id != task.user?.id) {
                throw ResponseStatusException(HttpStatus.FORBIDDEN)
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
            var tasks: List<Task> = tasksService.getAllByUser(user)
            for (task in tasks){
                tasksService.delete(task)
            }
        }

    }
}