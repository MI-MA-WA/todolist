package de.thkoeln.gm.todolistgdwdemo.tasks

import java.util.*

class Task {

    var name: String = "";

    var open: Boolean = false;

    var createdAt: Date = Date();

    override fun toString(): String {
        return "Name: $name, open: $open, createdAt: $createdAt"
    }
}