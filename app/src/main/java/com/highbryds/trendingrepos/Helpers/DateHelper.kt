package com.highbryds.trendingrepos.Helpers


import com.highbryds.trendingrepos.Configurations.App
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DateHelper {
    @Inject
    lateinit var now: Date
    @Inject
    lateinit var dateFormat: SimpleDateFormat


    init {
        App.app.appLevelComponent.inject(this@DateHelper)
    }

    /**
     * Calculate Date Difference
     *
     * This method is used to calculate hour differnce .
     *
     * @param String Date .
     * @return long  time difference
     *
     *
     */
    fun calculateDateDifference(newsDate: String): Long {


        var difference: Long = 0
        try {
            val newsdate = dateFormat!!.parse(newsDate)
            val current_date = dateFormat!!.parse(now.toString())


            difference = TimeUnit.MILLISECONDS.toHours(current_date!!.time - newsdate.time)

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return difference
    }
    //}
}
