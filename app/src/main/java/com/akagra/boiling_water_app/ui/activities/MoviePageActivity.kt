package com.akagra.boiling_water_app.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.akagra.boiling_water_app.extra.GoogleAuth
import com.akagra.boiling_water_app.extra.AdapterDataChanger
import com.akagra.boiling_water_app.R
import com.akagra.boiling_water_app.data.Comment
import com.akagra.boiling_water_app.data.CommentDAO
import com.akagra.boiling_water_app.databinding.ActivityMoviePageBinding
import com.akagra.boiling_water_app.ui.CommentsRVAdapter
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import org.json.JSONObject


class MoviePageActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMoviePageBinding
    private lateinit var adapter: CommentsRVAdapter
    private lateinit var movieID:String

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMoviePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getDataFromPrevActivity()
        createUI()
        setupRecyclerViewforComments()

        val googleAuth = GoogleAuth(this)

        // Enabling scrolling in comment box
        binding.commentbox.setOnTouchListener { view, event ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            if ((event.action and MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                view.parent.requestDisallowInterceptTouchEvent(false)
            }
            return@setOnTouchListener false
        }


        binding.commentButton.setOnClickListener{
            val movieID = movieID
            val comment = binding.commentbox.editableText.toString().trim()
//            val displayName = intent.getStringExtra("DisplayName")
//            val displayImg = intent.getStringExtra("DisplayImg")
            val displayName = googleAuth.acct?.displayName
            val displayImg = googleAuth.acct?.photoUrl.toString()

            val commentObj = Comment(
                movieId = movieID.toInt(),
                imageURL = displayImg,
                displayName = displayName,
                commentText = comment,
                createdAt = System.currentTimeMillis()
            )

            CommentDAO(this).setComment(commentObj,movieID,binding)
        }

    }

    private fun getDataFromPrevActivity(){
        movieID = intent.getIntExtra("id",10).toString()
    }

    private fun setupRecyclerViewforComments() {
        val collection = CommentDAO(this).db.collection(movieID)
        val query = collection.orderBy("createdAt",Query.Direction.DESCENDING)
        val RVOption = FirestoreRecyclerOptions.Builder<Comment>().setQuery(query, Comment::class.java).build()
        println("RVOPTION ${RVOption.snapshots}")
        adapter = CommentsRVAdapter(RVOption)
        binding.commentRV.adapter = adapter
        val obs = AdapterDataChanger(binding,adapter)
        adapter.registerAdapterDataObserver(obs)
    }

    @SuppressLint("SetTextI18n")
    private fun createUI(){
        if (intent.hasExtra("more_data")) {
            val mJsonObject = intent.getStringExtra("more_data")?.let { JSONObject(it) }
            Glide.with(binding.backdrop.context).load(mJsonObject?.getString("backdrop")).error(R.drawable.pepe)
                .into(binding.backdrop)
            binding.titleMoviepage.text = mJsonObject?.getString("title")
            binding.plot.text = mJsonObject?.getString("plot")
            binding.ImdbRating.text = "IMDB RATING : ${mJsonObject?.getString("imdb_rating")}"
            binding.Language.text = "LANGUAGE : ${mJsonObject?.getString("language")}"
            binding.releaseDateMoviepage.text = "RELEASE DATE : ${mJsonObject?.getString("release_date")}"
            binding.runtimeMoviepage.text = "RUNTIME : ${mJsonObject?.getString("runtime")}"
            binding.cast.text = "CAST : ${mJsonObject?.getString("cast")}"
        }else{
            Toast.makeText(this,"Some error occured",Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
