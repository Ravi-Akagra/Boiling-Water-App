package com.akagra.boiling_water_app.ui.activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.akagra.boiling_water_app.extra.GoogleAuth
import com.akagra.boiling_water_app.R
import com.akagra.boiling_water_app.data.Movie
import com.akagra.boiling_water_app.databinding.ActivityHomePageBinding
import com.akagra.boiling_water_app.ui.IMoviesRVAdapter
import com.akagra.boiling_water_app.ui.MoviesRVAdapter
import com.akagra.boiling_water_app.ui.Pagination
import com.akagra.boiling_water_app.ui.viewmodels.MovieViewModel


class HomePageActivity : AppCompatActivity(), IMoviesRVAdapter {
    private lateinit var binding: ActivityHomePageBinding
//    private lateinit var displayName:String
//    private lateinit var displayImg:String
    private val movieViewModel: MovieViewModel = MovieViewModel()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView = binding.recyclerview
        val adapter = MoviesRVAdapter(this, this)
        recyclerView.adapter = adapter

//        getDataFromPrevActivity()

        binding.burger.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton(R.string.yes_logout,
                    DialogInterface.OnClickListener { dialog, which ->
                        val googleAuth = GoogleAuth(this)
                        googleAuth.gsc?.signOut()
                        goToSignInActivity()
                    })
                .setNegativeButton(R.string.cancel, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
        movieViewModel.setAdapterPage(adapter,this,1)

        Pagination(this).paginationThroughOnScroll(movieViewModel,recyclerView,movieViewModel.getPageCount(),adapter)

    }

//    private fun getDataFromPrevActivity(){
//        intent.getStringExtra("DisplayName")?.let {
//            displayName = it
//        }
//        intent.getStringExtra("DisplayImg")?.let {
//            displayImg = it
//        }
//    }

    override fun onViewClicked(movie: Movie) {
        val intent = Intent(this, MoviePageActivity::class.java)
        intent.putExtra("more_data",movie.more_data.toString())
        intent.putExtra("id",movie.id)
//        intent.putExtra("DisplayImg", displayImg)
//        intent.putExtra("DisplayName",displayName)
        startActivity(intent)
    }

//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        println("menuuuu ${binding.burger?.menu}")
//
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.logoutBtn){
//            AlertDialog.Builder(this)
//                .setTitle("Logout")
//                .setMessage("Are you sure you want to logout?")
//                .setPositiveButton(R.string.yes_logout,
//                    DialogInterface.OnClickListener { dialog, which ->
//                        val googleAuth = GoogleAuth(this)
//                        googleAuth.gsc?.signOut()
//                        goToSignInActivity()
//                    })
//                .setNegativeButton(R.string.cancel, null)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show()
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun goToSignInActivity() {
        finish()
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }
}
