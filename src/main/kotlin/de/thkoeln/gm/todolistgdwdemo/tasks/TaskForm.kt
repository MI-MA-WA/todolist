package de.thkoeln.gm.todolistgdwdemo.tasks

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

class TaskForm {
    @Min(1) @Max(100) var name: String = ""
}