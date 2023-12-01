package de.thkoeln.gm.todolistgdwdemo.holidays

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@RestController
class HolidaysRestController (private val holidaysService: HolidaysService) {
    @GetMapping("/holidays")
    fun getAllHolidays(): List<Holiday> {
        return holidaysService.getHolidays()
    }

    @GetMapping(value = ["/holidays"], params = ["date"])
    fun getHoliday(date: LocalDate): Holiday {
        val holiday = holidaysService.getHolidayByDate(date)
        if(holiday != null){
            return holiday
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}