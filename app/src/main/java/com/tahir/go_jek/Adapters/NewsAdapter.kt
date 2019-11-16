package com.tahir.go_jek.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager

import com.squareup.picasso.Picasso
import com.tahir.go_jek.Helpers.DateHelper
import com.tahir.go_jek.Interfaces.NewsListInterface
import com.tahir.go_jek.Models.BaseTrending
import com.tahir.go_jek.R

import javax.inject.Inject


class NewsAdapter(
    internal var context: Context,
    internal var articles: List<BaseTrending>?
)// DaggerDateComponent.create().inject(this);
    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    // @BindView(R.id.share)
    //ImageView share;
    @Inject
    internal var dh: DateHelper? = null

    fun loadItems(newItems: List<BaseTrending>, ni: NewsListInterface) {

        articles = newItems
        ni.ifListisEmpty(articles!!.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.repo_list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        try {
            holder.name!!.text = articles!![position].author
            //   holder.published.setText(dh.calculateDateDifference(articles.get(position).getPublishedAt()));
            holder.heading!!.text = articles!![position].name
            holder.desc!!.text = articles!![position].description

            Picasso.get().load(articles!![position].avatar).into(holder.img)

            holder.lang!!.text = articles!![position].language
            holder.star!!.text = articles!![position].stars

            holder.fork!!.text = articles!![position].forks

            holder.cardView!!.setOnClickListener {
                TransitionManager.beginDelayedTransition(holder.cardView!!)

                if (holder.desc!!.visibility == View.GONE && holder.last_line!!.visibility == View.GONE) {
                    holder.desc!!.visibility = View.VISIBLE

                    holder.last_line!!.visibility = View.VISIBLE
                } else {

                    holder.desc!!.visibility = View.GONE

                    holder.last_line!!.visibility = View.GONE
                }
            }

            /* holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GeneralHelper.shareNews(articles.get(position).getTitle(), articles.get(position).getUrl(), context);
                }
            });*/
        } catch (e: Exception) {
            //eat this one.

        }

    }

    override fun getItemCount(): Int {
        return if (articles != null) {

            articles!!.size
        } else {

            0
        }

    }


    inner class NewsViewHolder
    // TextView published;

        (itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView? = null
        internal var img: ImageView? = null
        internal var heading: TextView? = null
        internal var desc: TextView? = null
        internal var lang: TextView? = null
        internal var star: TextView? = null
        internal var fork: TextView? = null
        internal var cardView: CardView? = null
        internal var last_line: LinearLayout? = null
    }


}