package de.thkoeln.gm.todolistgdwdemo.holidays

import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDate
import kotlin.collections.ArrayList

@Service
class HolidaysServiceImpl (
    private val client : HttpClient  = HttpClient.newBuilder().build(),
) : HolidaysService  {

     override fun getHolidays(): List<Holiday> {
         val request = HttpRequest.newBuilder()
             .GET()
             .uri(URI.create("https://feiertage-api.de/api/?nur_land=NW"))
             .build();
         val response = client.send(request, HttpResponse.BodyHandlers.ofString())

         val json = JSONObject(response.body())
         val holidays : ArrayList<Holiday> = ArrayList<Holiday>()

         json.keySet().forEach{ key ->
             val holidayJson : JSONObject = json.getJSONObject(key)
             val holiday : Holiday = Holiday()
             holiday.name = key
             holiday.date = LocalDate.parse(holidayJson.getString("datum"))
             holidays.add(holiday)
         }

         return holidays
    }

    override fun getHolidayByDate(date: LocalDate): Holiday? {
        val holidays: List<Holiday> = getHolidays()
        for (holiday in holidays) {
            if (holiday.date.year == date.year
                && holiday.date.month == date.month
                && holiday.date.dayOfMonth == date.dayOfMonth
            ) {
                return holiday
            }
        }
        return null
    }

    override fun getHolidaysByYear(year: Long): List<Holiday> {
        TODO("Not yet implemented")
    }

    override fun getHolidaysByState(state: String): List<Holiday> {
        TODO("Not yet implemented")
    }

    override fun getHolidaysByYearAndState(year: Long, state: String) : List<Holiday> {
        TODO("Not yet implemented")
    }


}