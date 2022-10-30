package com.akagra.boiling_water_app.extra

import androidx.recyclerview.widget.RecyclerView
import com.akagra.boiling_water_app.databinding.ActivityMoviePageBinding
import com.akagra.boiling_water_app.ui.CommentsRVAdapter

class AdapterDataChanger(private val binding: ActivityMoviePageBinding, private val adapter: CommentsRVAdapter) : RecyclerView.AdapterDataObserver() {
    override fun onChanged() {
        super.onChanged()
        println("Helooooooooooooaoaoaoao ${adapter.itemCount}")

//        if (adapter.itemCount==0){
//            binding.noCommentsYet.visibility = View.VISIBLE
//        }else{
//            binding.noCommentsYet.visibility = View.GONE
//        }
    }
}