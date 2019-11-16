package com.tahir.go_jek.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.tahir.go_jek.Models.BaseTrending

@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(items: List<BaseTrending>)

    @Query("SELECT * from articles")
    fun getallItems(): LiveData<List<BaseTrending>>

    @Query("DELETE FROM articles")
    fun delete()


    @Query("SELECT * from articles ORDER BY stars DESC")
     fun sortbyStars(): List<BaseTrending>


    @Query("SELECT * from articles ORDER BY name")
     fun sortbyName(): List<BaseTrending>
}

