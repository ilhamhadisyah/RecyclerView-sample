package com.ilham.dicoding

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.toBitmap
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var titleDetail : TextView
    private lateinit var ownerDetail : TextView
    private lateinit var descDetail : TextView
    private lateinit var imageDetail : ImageView
    private lateinit var shareBtn : Button
    private lateinit var back : Toolbar

    var title : String? = ""
    var ownerInfo : String? = ""
    var description : String? = ""
    var img : Int? = 0
    var position : Int? = 0

    companion object{
        const val TITLE = "title"
        const val OWNER = "owner"
        const val IMAGE = "Image"
        const val DESC = "Desc"
        const val ITEM_POSITION = "Position"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        titleDetail =findViewById(R.id.titleDetail)
        ownerDetail= findViewById(R.id.owner_detail)
        descDetail = findViewById(R.id.desc_detail)
        imageDetail = findViewById(R.id.image_detail)
        shareBtn = findViewById(R.id.share_btn_detail)
        back = findViewById(R.id.back_btn)

        title = intent.getStringExtra(TITLE)
        ownerInfo = intent.getStringExtra(OWNER)
        description = intent.getStringExtra(DESC)
        img = intent.getIntExtra(IMAGE,0)
        position = intent.getIntExtra(ITEM_POSITION,0)

        titleDetail.setText(title)
        ownerDetail.setText("Works By "+ownerInfo)
        descDetail.setText(description)
        imageDetail.setImageResource(img!!)

        back.setOnClickListener(clickListener)
        shareBtn.setOnClickListener(clickListener)
    }

    val clickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.back_btn ->{
                onBackPressed()
            }
            R.id.share_btn_detail ->{
                position?.let { showShareIntent(it) }
            }
        }
    }
    private fun showShareIntent(index : Int) {
        val imageDraw : Drawable = resources.getDrawable(portofolioData.listData[index].photo)
        val imageBitmap : Bitmap = imageDraw.toBitmap()
        val path = MediaStore.Images.Media.insertImage(contentResolver,imageBitmap,"share"+"-"+(Calendar.getInstance().time),null)
        val uriPath : Uri = Uri.parse(path)
        val shareTo  = Intent(Intent.ACTION_SEND)
        shareTo.setType("image/*")
        shareTo.putExtra(Intent.EXTRA_STREAM,uriPath)
        shareTo.putExtra(Intent.EXTRA_TEXT, "Check This Out !!! \nWorks by ${userData.getUserData[0].username}")
        shareTo.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(shareTo)
    }
}