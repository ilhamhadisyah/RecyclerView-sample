package com.ilham.dicoding

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import java.util.*
import kotlin.collections.ArrayList


class cardMemberAdapter(private val listPortofolio: ArrayList<portofolio>) :
    RecyclerView.Adapter<cardMemberAdapter.cardViewHolder>() {

    inner class cardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var portofolioImg: ImageView = itemView.findViewById(R.id.img_item_photo)
        var title: TextView = itemView.findViewById(R.id.tv_item_name)
        var ownerName: TextView = itemView.findViewById(R.id.tv_item_detail)
        var share: Button = itemView.findViewById(R.id.btn_set_share)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                val mov = Intent(itemView.context, DetailActivity::class.java)
                mov.putExtra(DetailActivity.TITLE, portofolioData.listData[position].title)
                mov.putExtra(DetailActivity.OWNER, portofolioData.listData[position].ownerName)
                mov.putExtra(DetailActivity.DESC, portofolioData.listData[position].description)
                mov.putExtra(DetailActivity.IMAGE, portofolioData.listData[position].photo)
                mov.putExtra(DetailActivity.ITEM_POSITION, position)
                itemView.context.startActivity(mov)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): cardMemberAdapter.cardViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_member_new, parent, false)
        return cardViewHolder(view)
    }

    override fun onBindViewHolder(holder: cardMemberAdapter.cardViewHolder, position: Int) {
        val porto = listPortofolio[position]
        fun showShareIntent() {
            val imageDraw: Drawable =
                holder.itemView.context.resources.getDrawable(portofolioData.listData[position].photo)
            val imageBitmap: Bitmap = imageDraw.toBitmap()
            val path = MediaStore.Images.Media.insertImage(
                holder.itemView.context.contentResolver,
                imageBitmap,
                "share"+"-"+(Calendar.getInstance().time),
                null
            )
            val uriPath: Uri = Uri.parse(path)
            val shareTo = Intent(Intent.ACTION_SEND)
            shareTo.setType("*/*")
            shareTo.putExtra(Intent.EXTRA_STREAM, uriPath)
            shareTo.putExtra(
                Intent.EXTRA_TEXT,
                "Check This Out !!! \nWorks by ${userData.getUserData[0].username}"
            )
            shareTo.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            holder.itemView.context.startActivity(Intent.createChooser(shareTo, "Share Image"))
        }

        Glide.with(holder.itemView.context)
            .load(porto.photo).into(holder.portofolioImg)
        holder.title.text = porto.title
        holder.ownerName.text = "Works by " + porto.ownerName
        holder.share.setOnClickListener { showShareIntent() }
    }

    override fun getItemCount(): Int {
        return listPortofolio.size
    }
}