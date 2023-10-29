package de.thkoeln.gm.todolistgdwdemo.tasks

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

@Controller
class TasksController {

    @GetMapping("/tasks")
    @ResponseBody
    fun saveTask(name: String): String {
        var task: Task = Task()
        task.name = name
        task.createdAt = Date()
        task.open = true
        return task.toString()
    }
}