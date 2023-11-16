package de.thkoeln.gm.todolistgdwdemo.tasks

import de.thkoeln.gm.todolistgdwdemo.users.User
import java.util.*

interface TasksService {
    fun findById(id: UUID): Task?
    fun findAllTasks(): List<Task>
    fun findAllOpenTask(): List<Task>
    fun findAllClosedTask(): List<Task>
    fun getAllOpenByUser(user: User): List<Task>
    fun delete(task: Task)
    fun save(task: Task)
}