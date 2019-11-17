package com.tahir.go_jek.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trendingrepos")

class BaseTrending {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    var forks: String? = null

    var author: String? = null

    var name: String? = null

    var description: String? = null

    var language: String? = null

    var avatar: String? = null

    var languageColor: String? = null

    var stars: Int? = null

    var url: String? = null

    var currentPeriodStars: String? = null


}