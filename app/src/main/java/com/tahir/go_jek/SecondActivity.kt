package com.tahir.go_jek

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tahir.go_jek.Activities.BaseActivity
import com.tahir.go_jek.Adapters.NewsAdapter
import com.tahir.go_jek.Interfaces.NewsListInterface
import com.tahir.go_jek.Models.BaseTrending
import com.tahir.go_jek.ViewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : BaseActivity(), NewsListInterface {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun ifListisEmpty(count: Int) {
        if (adapter.itemCount === 0) {
            rv_repos?.setVisibility(View.GONE)
          empty_view?.setVisibility(View.VISIBLE)
        } else {
            rv_repos?.setVisibility(View.VISIBLE)
            empty_view?.setVisibility(View.GONE)
        }
    }

    lateinit var newsViewModel: MainActivityViewModel
   //  lateinit var rv_repos: RecyclerView
    lateinit var adapter: NewsAdapter
    internal var list: List<BaseTrending>? = null

  //  lateinit var emptyView: TextView

    internal var a: List<BaseTrending>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        newsViewModel.allItems.observe(this,
            Observer<List<BaseTrending>> { articles ->
                a = articles
                adapter.loadItems(articles, this)
                adapter.notifyDataSetChanged()
            })
    }

    fun init() {

     //  val  rv_repos = findViewById(R.id.rv_repos) as RecyclerView
        rv_repos?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

       // rv_repos.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        // setting up recyclerview and also binding activity with the view-model
        newsViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        adapter = NewsAdapter(this, list)
       // rv_repos?.setLayoutManager(LinearLayoutManager(this))



        //linearLayoutManager = LinearLayoutManager(this)
       // rv_repos?.layoutManager = linearLayoutManager

        //val llm = LinearLayoutManager(this)
        //llm.orientation = LinearLayoutManager.VERTICAL
        //rv_repos?.setLayoutManager(llm)

      //  rv_repos?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rv_repos?.setAdapter(adapter)


        // pull to refresh
        pullToRefresh?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            Log.d("#####","on refresh");

            newsViewModel.callNewsAPI()
            pullToRefresh?.setRefreshing(false)
        })
    }
}
