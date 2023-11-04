package de.thkoeln.gm.todolistgdwdemo.tasks

import de.thkoeln.gm.todolistgdwdemo.users.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.GenericGenerator
import java.util.*
@Entity
class Task {
     @Id
     @GeneratedValue(generator = "uuid")
     @GenericGenerator(name="uuid2", strategy = "uuid2")
     var id: UUID = UUID.randomUUID()
     var name: String = ""
     var open: Boolean = false
     var createdAt: Date = Date()

     @ManyToOne
     var user: User? = null

    override fun toString(): String {
        return "[Name: $name, open: $open, createdAt: $createdAt]"
    }
}