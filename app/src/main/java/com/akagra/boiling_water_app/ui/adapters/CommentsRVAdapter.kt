package com.akagra.boiling_water_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akagra.boiling_water_app.R
import com.akagra.boiling_water_app.extra.Utils
import com.akagra.boiling_water_app.data.Comment

import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class CommentsRVAdapter(options: FirestoreRecyclerOptions<Comment>) : FirestoreRecyclerAdapter<Comment, CommentViewHolder>(
    options
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val viewHolder = CommentViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.comment_tile,
            parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int, model: Comment) {
        println("MODALLLLL ${model.displayName}")
        holder.displayName.text = model.displayName
        holder.commentText.text = model.commentText
        val imgURL = if (model.imageURL == "null"){
            "https://ui-avatars.com/api/?name=${model.displayName}&background=random"
        }else{
            model.imageURL
        }
        Glide.with(holder.itemView.context).load(imgURL)
            .error(R.drawable.pepe).into(holder.displayPic)
        holder.createdAt.text = Utils.getTimeAgo(model.createdAt)
    }


}
//class CommentsRVAdapter(val context: Context) : RecyclerView.Adapter<CommentViewHolder>() {
//
//    private lateinit var comments:ArrayList<Comment>
//
//
//    override fun getItemCount(): Int {
//        return comments.size
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun updateComments(newComments:ArrayList<Comment>){
//        comments.clear()
//        comments.addAll(newComments)
//        notifyDataSetChanged()
//    }
//}

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val displayPic:ImageView = itemView.findViewById(R.id.userImage)
    val displayName:TextView = itemView.findViewById(R.id.userName)
    val commentText:TextView = itemView.findViewById(R.id.postTitle)
    val createdAt:TextView = itemView.findViewById(R.id.createdAt)
}
