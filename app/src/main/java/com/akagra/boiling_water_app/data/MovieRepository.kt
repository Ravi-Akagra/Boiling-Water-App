package com.akagra.boiling_water_app.data

import android.content.Context
import com.akagra.boiling_water_app.ui.MoviesRVAdapter

class MovieRepository(private val movieDAO: MovieDAO) {

    fun setAdapterPage(adapter:MoviesRVAdapter,context:Context,page:Int){
        movieDAO.getPage(context,page,object : VolleyCallback{
            override fun onSuccessResponse(result: ArrayList<Movie>) {
                adapter.updateMovies(result)
            }

        })
    }

    fun getPageCount(): Int {
        return movieDAO.getPageCount()
    }
}