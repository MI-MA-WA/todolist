package de.thkoeln.gm.todolistgdwdemo.tasks

import de.thkoeln.gm.todolistgdwdemo.users.User
import java.util.*

interface TasksService {
    fun findById(id: UUID): Task?
    fun findAll(): List<Task>
    fun findAllOpen(): List<Task>
    fun findAllClosed(): List<Task>
    fun getAllOpenByUser(user: User): List<Task>
    fun getAllClosedByUser(user: User): List<Task>
    fun getAllByUser(user: User): List<Task>
    fun delete(task: Task)
    fun save(task: Task)
}