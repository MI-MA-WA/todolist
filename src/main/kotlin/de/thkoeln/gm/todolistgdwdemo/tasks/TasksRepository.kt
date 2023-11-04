package de.thkoeln.gm.todolistgdwdemo.tasks

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TasksRepository : CrudRepository<Task, UUID>{
    fun findByOpenTrue() : List<Task>
    fun findByOpenFalse() : List<Task>

    @Query(value = "SELECT t FROM Task t WHERE t.open = true")
    fun getAllOpen() : List<Task>

    @Query(value = "SELECT t FROM Task t WHERE t.open = false")
    fun getAllClosed(): List<Task>
}