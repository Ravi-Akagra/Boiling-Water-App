package com.akagra.boiling_water_app.ui

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.akagra.boiling_water_app.ui.viewmodels.MovieViewModel

class Pagination(private val context: Context){
    fun paginationThroughOnScroll(viewModel: MovieViewModel,recyclerView: RecyclerView, MAX_PAGE:Int,adapter:MoviesRVAdapter) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var page = 1
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
//                    Toast.makeText(this@HomePageActivity, "Last", Toast.LENGTH_LONG).show()
                    page+=1
                    if (page<=MAX_PAGE) {
                        viewModel.setAdapterPage(adapter,context,page)
                    }
                    else{
                        Toast.makeText(context, "No More Movies to display",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}