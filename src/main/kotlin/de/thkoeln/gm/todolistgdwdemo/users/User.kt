package de.thkoeln.gm.todolistgdwdemo.users

import de.thkoeln.gm.todolistgdwdemo.tasks.Task
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*
import kotlin.collections.ArrayList


@Entity
@Table(name="APP_USER")
class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    var id: UUID = UUID.randomUUID()
    var email: String = ""

    @OneToMany
    var tasks: List<Task> = ArrayList()

    override fun toString(): String {
        //val taskList : String = tasks.joinToString(";")
        //return "Email: $email (tasks: $taskList)"
        return "Id: $id, Email: $email"
    }
}