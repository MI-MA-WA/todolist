package de.thkoeln.gm.todolistgdwdemo.tasks

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
class TasksController (private val tasksService: TasksService) {

    @PostMapping("/tasks")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun saveTask(name: String): String {
        var task: Task = Task()
        task.name = name
        tasksService.save(task)
        return task.toString()
    }

    @GetMapping("/tasks")
    @ResponseBody
    fun getAllTasks(): String {
        val tasks: List<Task> = tasksService.findAllTasks()
        return tasks.joinToString(";")
    }

    @GetMapping("/tasks/{id}")
    @ResponseBody
    fun getTask(@PathVariable id: UUID): String {
        var task = tasksService.findById(id)
        if(task != null){
            return task.toString()
        } else {
            throw ChangeSetPersister.NotFoundException()
        }
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateTask(@PathVariable id: UUID, open: Boolean?, name: String?){
        var task = tasksService.findById(id)
        if (task != null) {

            if (open != null) {
                task.open = open
            }

            if (name != null){
                task.name = name
            }

            tasksService.save(task)
        } else {
            throw ChangeSetPersister.NotFoundException()
        }

    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: UUID){
        var savedTask = tasksService.findById(id)
        if(savedTask != null){
            tasksService.delete(savedTask)
        }
    }

    @DeleteMapping("/tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllTasks(){
        var tasks = tasksService.findAllTasks()
        for (task in tasks){
            tasksService.delete(task)
        }
    }
}