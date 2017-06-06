package com.cinecor.android.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    var isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    var hhmmFormat = SimpleDateFormat("HH:mm", Locale.US)

    fun String.formatedHour(): String? {
        isoFormat.timeZone = TimeZone.getTimeZone("UTC")
        hhmmFormat.timeZone = TimeZone.getTimeZone("Europe/Madrid")
        try {
            return DateUtils.hhmmFormat.format(DateUtils.isoFormat.parse(this))
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }
    }

    fun String.isAfterNow(): Boolean {
        isoFormat.timeZone = TimeZone.getTimeZone("UTC")
        try {
            return DateUtils.isoFormat.parse(this).after(Date())
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
        }
    }
}
