package com.rapysoft.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rapysoft.myapplication.R
import com.rapysoft.myapplication.constant.AppConstant
import com.rapysoft.myapplication.models.ModelMovie
import com.squareup.picasso.Picasso

class AdapterMovie(
    private val data: ArrayList<ModelMovie>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<AdapterMovie.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMovieTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val ivMovie: ImageView = itemView.findViewById(R.id.imageViewMovie)
        val ratingBarMovie: RatingBar = itemView.findViewById(R.id.ratingBarMovie)
        val tvVoteAverage: TextView = itemView.findViewById(R.id.textViewVoteAverage)
        val tvDate: TextView = itemView.findViewById(R.id.textViewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_movile, parent, false)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {

        val title = data[i].title ?: "Title"

        val url = "${AppConstant.constUrlImage}${data[i].posterPath}"

        val voteAverage = data[i].voteAverage.toFloat() / 2

        val releaseDate=data[i].releaseDate


        holder.tvDate.text=releaseDate


        holder.tvMovieTitle.text = title

        holder.ratingBarMovie.rating = voteAverage

        holder.tvVoteAverage.text = voteAverage.toString()

        Picasso.get()
            .load(url)
            .resize(500, 500)
            .centerInside()
            .into(holder.ivMovie)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it.rootView, i)
        }
    }

    override fun getItemCount(): Int {
        return data.size;
    }
}