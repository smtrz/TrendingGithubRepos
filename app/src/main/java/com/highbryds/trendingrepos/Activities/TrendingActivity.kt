package com.highbryds.trendingrepos.Activities

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.highbryds.trendingrepos.Adapters.TrendingRepoAdapter
import com.highbryds.trendingrepos.Helpers.ProgressDialogHelper
import com.highbryds.trendingrepos.Interfaces.NewsListInterface
import com.highbryds.trendingrepos.Models.BaseTrending
import com.highbryds.trendingrepos.R
import com.highbryds.trendingrepos.ViewModels.TrendingActivityViewModel
import kotlinx.android.synthetic.main.act_toolbar.*
import kotlinx.android.synthetic.main.activity_main.*


class TrendingActivity : AppCompatActivity(), NewsListInterface, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.more -> {

                popMenu()

            }
            R.id.btn_layout -> {
                newsViewModel.callNewsAPI()


            }
        }


    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progressdialog: ProgressDialog;
    override fun ifListisEmpty(count: Int) {
        if (adapter.itemCount === 0) {
            rv_repos?.setVisibility(View.GONE)
            btn_layout.setVisibility(View.VISIBLE)
            error_layout.setVisibility(View.VISIBLE)
            // empty_view?.setVisibility(View.VISIBLE)
        } else {
            rv_repos?.setVisibility(View.VISIBLE)
            error_layout.setVisibility(View.GONE)
            btn_layout.setVisibility(View.VISIBLE)

            //  empty_view?.setVisibility(View.GONE)
        }
    }

    lateinit var newsViewModel: TrendingActivityViewModel
    lateinit var adapter: TrendingRepoAdapter
    internal var list: List<BaseTrending>? = null


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

        newsViewModel.ifDataIsloading().observe(this,
            Observer { aBoolean ->
                if (aBoolean!!) {
                    val ph = ProgressDialogHelper()

                    progressdialog = ph.showDialog(this@TrendingActivity)

                } else {
                    if (progressdialog != null) {
                        ProgressDialogHelper.cancelDialog(progressdialog)
                    }
                }
            })

    }

    fun popMenu() {

        val popupMenu: PopupMenu = PopupMenu(this, more)
        popupMenu.menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.action_sort_star -> {
                    adapter.loadItems(newsViewModel.sorted_allItems_bystar, this)
                    adapter.notifyDataSetChanged()
                }
                R.id.action_sort_name -> {

                    adapter.loadItems(newsViewModel.sorted_allItems_byname, this)
                    adapter.notifyDataSetChanged()

                }

            }

            true
        })
        popupMenu.show()
    }

    fun init() {

        more.setOnClickListener(this)
        btn_layout.setOnClickListener(this)
        rv_repos?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // setting up recyclerview and also binding activity with the view-model
        newsViewModel = ViewModelProviders.of(this).get(TrendingActivityViewModel::class.java)
        adapter = TrendingRepoAdapter(this, list)
        rv_repos?.setAdapter(adapter)
        // pull to refresh
        pullToRefresh?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            newsViewModel.callNewsAPI()
            pullToRefresh?.setRefreshing(false)
        })


    }
}
