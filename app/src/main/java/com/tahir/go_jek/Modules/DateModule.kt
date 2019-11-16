package com.tahir.go_jek.Modules

import com.tahir.go_jek.Helpers.DateHelper

import java.text.SimpleDateFormat
import java.util.Date

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
object DateModule {
    internal val date: Date
        @Provides
        @Singleton
        get() = Date()

    internal val dateFormat: SimpleDateFormat
        @Provides
        @Singleton
        get() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")

    internal val dateHelper: DateHelper
        @Provides
        @Singleton
        get() = DateHelper()
}
