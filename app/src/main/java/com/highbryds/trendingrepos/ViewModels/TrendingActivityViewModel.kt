package com.highbryds.trendingrepos.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.highbryds.trendingrepos.Configurations.App
import com.highbryds.trendingrepos.Database.DbRepository
import com.highbryds.trendingrepos.Models.BaseTrending
import javax.inject.Inject

class TrendingActivityViewModel(application: Application) : AndroidViewModel(application) {

    // injecting repository
    @Inject
    lateinit var dbrepo: DbRepository


    val allItems: LiveData<List<BaseTrending>>
        get() = dbrepo!!.getallArticles()

    val sorted_allItems_bystar: List<BaseTrending>
        get() = dbrepo!!.get_sorted_list_star()

    val sorted_allItems_byname: List<BaseTrending>
        get() = dbrepo!!.get_sorted_list_name()


    init {


        App.app.appLevelComponent.inject(this)
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dbrepo!!.ifDataIsloading()

    }
    // just refresh the data based on the result.

    fun callNewsAPI() {
        dbrepo!!.getData()

    }

}


