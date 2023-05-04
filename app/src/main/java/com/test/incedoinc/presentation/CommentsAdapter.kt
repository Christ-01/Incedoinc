package com.test.incedoinc.presentation

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.incedoinc.domain.CommentEntity
import com.test.incedoinc.R

class CommentsAdapter(var clickCallback: (position: Int) -> Unit):
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    var comments: ArrayList<CommentEntity> = arrayListOf()
    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val ivUserImage: ImageView = itemView.findViewById(R.id.ivUserProfile)
        val tvUserId: TextView = itemView.findViewById(R.id.tvUserId)
        val tvBody: TextView = itemView.findViewById(R.id.tvBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.comments_item, parent, false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.tvUserName.text = comments[position].name
        holder.tvEmail.text = comments[position].email
        holder.tvBody.text = comments[position].body
        holder.tvUserId.text = comments[position].id.toString()
        comments[position].imageUri?.let {
            Glide.with(holder.itemView.context)
                .load(it)
                .circleCrop()
                .into(holder.ivUserImage)
        }
        holder.ivUserImage.setOnClickListener {
            clickCallback.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun updateUserProfile(position: Int, imageUri: Uri?) {
        val comment = comments[position].copy(
            imageUri = imageUri
        )
        comments[position] = comment
        notifyItemChanged(position)
    }
}