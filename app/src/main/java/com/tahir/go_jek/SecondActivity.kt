package com.tahir.go_jek

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tahir.go_jek.Adapters.NewsAdapter
import com.tahir.go_jek.Helpers.ProgressDialogHelper
import com.tahir.go_jek.Interfaces.NewsListInterface
import com.tahir.go_jek.Models.BaseTrending
import com.tahir.go_jek.ViewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.act_toolbar.*
import kotlinx.android.synthetic.main.activity_main.*


class SecondActivity : AppCompatActivity(), NewsListInterface, View.OnClickListener {
    override fun onClick(v: View?) {
        popMenu()


    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progressdialog: ProgressDialog;
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
    lateinit var adapter: NewsAdapter
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

                    progressdialog = ph.showDialog(this@SecondActivity)

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
        // popupMenu.show()
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.action_sort_star -> {
                    // newsViewModel.allstarsortedItems
                    adapter.loadItems(newsViewModel.sorted_allItems_bystar, this)
                    adapter.notifyDataSetChanged()
                  //  Toast.makeText(this@SecondActivity, item.title, Toast.LENGTH_SHORT).show();
                }
                R.id.action_sort_name -> {

                    adapter.loadItems(newsViewModel.sorted_allItems_byname, this)
                    adapter.notifyDataSetChanged()
                    /*  adapter.loadItems(newsViewModel.allNamesortedItems.value, this)
                      Toast.makeText(this@SecondActivity, item.title, Toast.LENGTH_SHORT).show();

                      adapter.notifyDataSetChanged()*/

                    // Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show();
                }

            }

            true
        })
        popupMenu.show()
    }

    fun init() {
        more.setOnClickListener(this)
        rv_repos?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // setting up recyclerview and also binding activity with the view-model
        newsViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        adapter = NewsAdapter(this, list)
        rv_repos?.setAdapter(adapter)
        // pull to refresh
        pullToRefresh?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            newsViewModel.callNewsAPI()
            pullToRefresh?.setRefreshing(false)
        })


    }
}
