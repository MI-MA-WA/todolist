package de.thkoeln.gm.todolistgdwdemo.tasks

import de.thkoeln.gm.todolistgdwdemo.users.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
@Service
class TasksServiceImpl (private val tasksRepository: TasksRepository) : TasksService {
    override fun findById(id: UUID): Task? {
        return tasksRepository.findByIdOrNull(id);
    }

    override fun findAllTasks(): List<Task> {
        return tasksRepository.findAll().toList();
    }

    override fun findAllOpenTask(): List<Task> {
        return tasksRepository.findByOpenTrue()
        //return tasksRepository.getAllOpen()
    }

    override fun findAllClosedTask(): List<Task> {
        return tasksRepository.findByOpenFalse()
        //return tasksRepository.getAllClosed()
    }

    override fun getAllOpenByUser(user: User): List<Task> {
        return tasksRepository.findByOpenTrueAndUser(user)
    }

    override fun delete(task: Task){
        tasksRepository.delete(task)
    }

    override fun save(task: Task) {
        tasksRepository.save(task);
    }
}
