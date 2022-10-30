package com.akagra.boiling_water_app.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akagra.boiling_water_app.R
import com.akagra.boiling_water_app.data.Movie
import com.bumptech.glide.Glide

class MoviesRVAdapter(private val context: Context, private val listener: IMoviesRVAdapter) : RecyclerView.Adapter<MovieViewHolder>(){

    val movies = ArrayList<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_tile,parent,false)
        val viewHolder = MovieViewHolder(view)
        view.setOnClickListener{
            listener.onViewClicked(movies[viewHolder.bindingAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentmovie = movies[position]
        if (currentmovie.poster != null){
        Glide.with(holder.itemView.context).load(currentmovie.poster).error(R.drawable.pepe).into(holder.poster)}
        else {Glide.with(holder.itemView.context).load(R.drawable.pepe).into(holder.poster)}
        holder.bwRating.text = currentmovie.bw_rating
        holder.title.text = currentmovie.title
        holder.runtime.text = currentmovie.runtime
        holder.releaseDate.text = currentmovie.release_date
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies:ArrayList<Movie>){
//        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}

interface IMoviesRVAdapter {
    fun onViewClicked(movie: Movie)
}

class MovieViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    fun bind(item: Movie?) {
        title.text = item?.title
        bwRating.text = item?.bw_rating
        releaseDate.text = item?.release_date
        runtime.text = item?.runtime
        Glide.with(itemView.context).load(item?.poster).into(poster)
    }

    val poster:ImageView = itemView.findViewById(R.id.poster)
    val title:TextView = itemView.findViewById(R.id.title)
    val bwRating:TextView = itemView.findViewById(R.id.bwRating)
    val releaseDate:TextView = itemView.findViewById(R.id.release_date)
    val runtime:TextView = itemView.findViewById(R.id.runtime)
}
