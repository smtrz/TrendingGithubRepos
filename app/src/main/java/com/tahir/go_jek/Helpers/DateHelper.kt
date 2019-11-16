package com.tahir.go_jek.Helpers


import com.tahir.go_jek.Components.DaggerDateComponent
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

import javax.inject.Inject

class DateHelper {
    @Inject
    lateinit var now: Date
    @Inject
    lateinit var dateFormat: SimpleDateFormat

   /* fun calculateDateDifference(newsDate: String): String? {
        DaggerDateComponent.create().inject(this)
        var difference: String? = null
        try {
            val newsdate = dateFormat!!.parse(newsDate)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(now!!.time - newsdate.time)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(now!!.time - newsdate.time)
            val hours = TimeUnit.MILLISECONDS.toHours(now!!.time - newsdate.time)
            val days = TimeUnit.MILLISECONDS.toDays(now!!.time - newsdate.time)
            if (seconds < 60) {
                difference = "$seconds seconds ago"

            } else if (minutes < 60) {
                difference = "$minutes minutes ago"

            } else if (hours < 24) {
                difference = "$hours hours ago"

            } else {
                difference = "$days days ago"

            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return difference
    }*/
}
