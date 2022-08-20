package com.learning.journey.base.core.data.model

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateTimeFormatter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    fun getFormattedDate(dateInString: String, format: String): String? {
        val fmtIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        fmtIn.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date = fmtIn.parse(dateInString)!!
        val fmtOut = SimpleDateFormat(format, Locale.US)
        return fmtOut.format(date)
    }

    fun getUTCFormattedDate(dateInput: Date): String? {
        val tz = TimeZone.getTimeZone("UTC")
        val fmtIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        fmtIn.timeZone = tz
        return fmtIn.format(dateInput)
    }

    fun getNotificationUTCFormattedDate(dateInput: Date): String? {
        val tz = TimeZone.getTimeZone("UTC")
        val fmtIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        fmtIn.timeZone = tz
        return fmtIn.format(dateInput)
    }

    fun getHoursAndMinutes(dateInString: String): String? {
        val fmtIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        fmtIn.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date = fmtIn.parse(dateInString)!!
        val fmtOut = SimpleDateFormat("h:mm a", Locale.US)
        return fmtOut.format(date)
    }

    fun isSameDay(dateInString: String?): Boolean {
        val calDate = Calendar.getInstance()
        val fmtIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        calDate.time = fmtIn.parse(dateInString)

        val currentCal = Calendar.getInstance()
        return isSameCalenderDate(calDate, currentCal)
    }

    fun isSameCalenderDate(cal1: Calendar?, cal2: Calendar?): Boolean {
        require(!(cal1 == null || cal2 == null)) { "The dates must not be null" }
        return cal1[Calendar.ERA] === cal2[Calendar.ERA] && cal1[Calendar.YEAR] === cal2[Calendar.YEAR] && cal1[Calendar.DAY_OF_YEAR] === cal2[Calendar.DAY_OF_YEAR]
    }
}