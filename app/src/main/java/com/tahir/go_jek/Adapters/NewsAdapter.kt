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
import com.tahir.go_jek.Interfaces.NewsListInterface
import com.tahir.go_jek.Models.BaseTrending
import com.tahir.go_jek.R
import kotlinx.android.synthetic.main.repo_list_item.view.*


class NewsAdapter(
    var context: Context,
    var articles: List<BaseTrending>?
)

    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    fun loadItems(newItems: List<BaseTrending>?, ni: NewsListInterface) {

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
            holder.star!!.text = articles!![position].stars.toString()

            holder.fork!!.text = articles!![position].forks

            holder.cardView!!.setOnClickListener {
                TransitionManager.beginDelayedTransition(holder.cardView!!)

                if (holder.third_line!!.visibility == View.GONE && holder.last_line!!.visibility == View.GONE) {
                    holder.third_line!!.visibility = View.VISIBLE

                    holder.last_line!!.visibility = View.VISIBLE
                } else {

                    holder.third_line!!.visibility = View.GONE

                    holder.last_line!!.visibility = View.GONE
                }
            }


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


    class NewsViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal var name: TextView? = itemView.name
        internal var img: ImageView? = itemView.image
        internal var heading: TextView? = itemView.heading
        internal var desc: TextView? = itemView.desc
        internal var lang: TextView? = itemView.lang
        internal var star: TextView? = itemView.star
        internal var fork: TextView? = itemView.fork
        internal var cardView: CardView? = itemView.cardView
        internal var last_line: LinearLayout? = itemView.last_line
        internal var third_line: LinearLayout? = itemView.third_layout
    }


}