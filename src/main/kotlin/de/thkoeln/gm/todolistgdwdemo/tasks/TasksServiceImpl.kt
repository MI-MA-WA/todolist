package de.thkoeln.gm.todolistgdwdemo.tasks

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
@Service
class TasksServiceImpl (private val tasksRepository: TasksRepository) : TasksService {
    override fun findById(id: UUID): Task? {
        var task: Task? = tasksRepository.findByIdOrNull(id);
        return task;
    }

    override fun findAllTasks(): List<Task> {
        return tasksRepository.findAll().toList();
    }

    override fun findAllOpenTask(): List<Task> {
        //return taskRepository.findByOpenTrue()
        return tasksRepository.getAllOpen()
    }

    override fun findAllClosedTask(): List<Task> {
        //return taskRepository.findByOpenFalse()
        return tasksRepository.getAllClosed()
    }

    override fun delete(task: Task){
        tasksRepository.delete(task)
    }

    override fun save(task: Task) {
        tasksRepository.save(task);
    }
}
