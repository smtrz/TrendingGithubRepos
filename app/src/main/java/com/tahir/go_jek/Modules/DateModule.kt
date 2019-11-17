package com.tahir.go_jek.Modules

import com.tahir.go_jek.Helpers.DateHelper
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Module
class DateModule {

    @Provides
    @Singleton
    fun getDate(): Date {

        return Date()
    }

    @Provides
    @Singleton
    fun getDateFormat(): SimpleDateFormat {

        return SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy")
    }

    val dateHelper: DateHelper
         @Provides
         @Singleton
         get() = DateHelper()
}

