package de.thkoeln.gm.todolistgdwdemo.holidays

import java.time.LocalDate

interface HolidaysService {

    fun getHolidays(): List<Holiday>
    fun getHolidayByDate(date: LocalDate): Holiday?

    //TODO
    fun getHolidaysByYear(year: Long): List<Holiday>
    fun getHolidaysByState(state: String): List<Holiday>
    fun getHolidaysByYearAndState(year: Long, state: String): List<Holiday>

}