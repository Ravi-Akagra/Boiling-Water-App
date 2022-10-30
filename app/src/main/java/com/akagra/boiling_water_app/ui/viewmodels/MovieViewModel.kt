package com.akagra.boiling_water_app.ui.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Adapter
import androidx.lifecycle.AndroidViewModel
import com.akagra.boiling_water_app.data.MovieDAO
import com.akagra.boiling_water_app.data.MovieRepository
import com.akagra.boiling_water_app.ui.MoviesRVAdapter

class MovieViewModel()  {
    private val repository:MovieRepository
    init {
        val dao = MovieDAO()
        repository = MovieRepository(movieDAO = dao)
    }


    fun getPageCount(): Int {
        return repository.getPageCount()
    }

    fun setAdapterPage(adapter: MoviesRVAdapter,context: Context,page:Int){
        repository.setAdapterPage(adapter,context,page)
    }
}