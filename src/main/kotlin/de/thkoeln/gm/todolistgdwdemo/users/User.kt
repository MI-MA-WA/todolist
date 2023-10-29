package de.thkoeln.gm.todolistgdwdemo.users

import de.thkoeln.gm.todolistgdwdemo.tasks.Task

class User {

    var email: String = ""
    var tasks: ArrayList<Task> = arrayListOf()

    override fun toString(): String {
        val taskList : String = tasks.joinToString(";")
        return "Email: ${email} (tasks: ${taskList})"
    }
}