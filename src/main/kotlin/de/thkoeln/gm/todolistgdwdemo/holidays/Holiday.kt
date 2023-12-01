package de.thkoeln.gm.todolistgdwdemo.holidays

import java.time.LocalDate

class Holiday {
    var name: String = ""
    var date: LocalDate = LocalDate.now()

    override fun toString(): String {
        return "$name $date"
    }
}