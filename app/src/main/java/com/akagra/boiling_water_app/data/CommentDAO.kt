package com.akagra.boiling_water_app.data

import android.content.Context
import android.widget.Toast
import com.akagra.boiling_water_app.databinding.ActivityMoviePageBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CommentDAO(private val context: Context) {
    val db = FirebaseFirestore.getInstance()


    @OptIn(DelicateCoroutinesApi::class)
    fun setComment(comment: Comment, movieID:String, binding:ActivityMoviePageBinding){

        if (comment.commentText?.length!! >= 4){
            val commentArray = db.collection(movieID)

            binding.commentbox.editableText.clear()
            GlobalScope.launch(Dispatchers.IO) {
//                commentArray.document(movieID).set(hashMapOf("${System.currentTimeMillis()}" to comment), SetOptions.merge())
                commentArray.document("${System.currentTimeMillis()}").set(comment)
            }
        }else {
            Toast.makeText(context,"Comment too small", Toast.LENGTH_SHORT).show()
        }
    }

//    @OptIn(DelicateCoroutinesApi::class)
//    fun getComments(movieID: Int):ArrayList<Comment>{
//        var data:Map<String,Any>
//        val document = db.collection("comments").document(movieID.toString())
//        GlobalScope.launch(Dispatchers.IO) {
//            data = document.get().result.data as Map<String, Any>
//        }
//
//
//        // rtype -> ArrayList of Comments, sorted with time
//    }
}