package com.highbryds.trendingrepos.Database


import androidx.room.Database
import androidx.room.RoomDatabase

import com.highbryds.trendingrepos.Models.BaseTrending

@Database(entities = [BaseTrending::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {


    abstract fun trendingDao(): TrendingRepoDao
}
