package com.tahir.go_jek.Database


import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahir.go_jek.Configurations.App
import com.tahir.go_jek.Interfaces.EndpointsInterface
import com.tahir.go_jek.Models.BaseTrending
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class DbRepository {

    lateinit var articleDao: ArticlesDao
    @Inject
    lateinit var retrofit: Retrofit
    internal var dataLoading = MutableLiveData<Boolean>()

    @Inject
    lateinit var c: Context
    @Inject
    lateinit var db: AppDB
    lateinit var sortedlist: List<BaseTrending>

    internal constructor() {
        // empty constructor

    }

    constructor(application: Context) {
        App.app.appLevelComponent.inject(this@DbRepository)
        articleDao = db!!.articleDao()
    }


    fun getallArticles(): LiveData<List<BaseTrending>> {
        getAllNews()
        return articleDao.getallItems()

    }

    fun get_sorted_list_star(): List<BaseTrending> {

        return getallArticles_sortedbystars(articleDao).execute().get()

    }

    fun get_sorted_list_name(): List<BaseTrending> {

        return getallArticles_sortedbyName(articleDao).execute().get()

    }

    fun deleteAllitems() {
        deleteAsync(articleDao).execute()

    }

    fun insertItems(sms: List<BaseTrending>?) {
        insertAsyncTask(articleDao).execute(sms)
    }

    private inner class getallArticles_sortedbystars internal constructor(private val mAsyncTaskDao: ArticlesDao): AsyncTask<Void, Void, List<BaseTrending>>() {
        override fun doInBackground(vararg url: Void): List<BaseTrending> {
           return mAsyncTaskDao.sortbyStars()
            //return null
        }
    }



    private inner class getallArticles_sortedbyName internal constructor(private val mAsyncTaskDao: ArticlesDao): AsyncTask<Void, Void, List<BaseTrending>>() {
        override fun doInBackground(vararg url: Void): List<BaseTrending> {
            return mAsyncTaskDao.sortbyName()
        }
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: ArticlesDao) :
        AsyncTask<List<BaseTrending>, Void, Void>() {

        override fun doInBackground(vararg params: List<BaseTrending>): Void? {
            mAsyncTaskDao.insertItem(params[0])
            return null
        }
    }

    private class deleteAsync internal constructor(private val mAsyncTaskDao: ArticlesDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {
            mAsyncTaskDao.delete()
            return null
        }
    }

    // network call to get all the news
    fun getAllNews() {
        dataLoading.value = true
        //  pd.show();
        val endpoints = retrofit!!.create(EndpointsInterface::class.java)
        endpoints.getNewsList("", "daily").enqueue(object : Callback<List<BaseTrending>> {
            override fun onResponse(
                call: Call<List<BaseTrending>>,
                response: Response<List<BaseTrending>>
            ) {
                dataLoading.value = false

                if (response.isSuccessful) {
                    deleteAllitems()
                    insertItems(response.body())
                } else {
                }
            }

            override fun onFailure(call: Call<List<BaseTrending>>, t: Throwable) {
                dataLoading.value = false

            }
        })
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dataLoading

    }
}
