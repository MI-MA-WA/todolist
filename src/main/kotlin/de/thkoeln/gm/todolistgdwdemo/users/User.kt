package de.thkoeln.gm.todolistgdwdemo.users

import de.thkoeln.gm.todolistgdwdemo.tasks.Task
import jakarta.persistence.*
import jakarta.validation.constraints.Email
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
    @Email var email: String = ""

    constructor(email: String){
        User()
        this.email = email
    }

    constructor()

    override fun toString(): String {
        //val taskList : String = tasks.joinToString(";")
        //return "Email: $email (tasks: $taskList)"
        return "Id: $id, Email: $email"
    }
}