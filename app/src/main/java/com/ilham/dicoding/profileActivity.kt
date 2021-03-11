package com.ilham.dicoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import de.hdodenhof.circleimageview.CircleImageView

class profileActivity : AppCompatActivity() {
    companion object{
        const val PROFILE_IMAGE = "PROFILE"
        const val USER = "NAME"
        const val EMAIL = "MAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val ppImage : CircleImageView = findViewById(R.id.tv_profile)
        val userName : TextView = findViewById(R.id.tv_name)
        val email : TextView = findViewById(R.id.tv_email)
        val backBtn : Toolbar = findViewById(R.id.back_btn_profile)
        backBtn.setOnClickListener(clickListener)

        val PP_IMAGE = intent.getIntExtra(PROFILE_IMAGE,0)
        val USERNAME = intent.getStringExtra(USER)
        val E_MAIL = intent.getStringExtra(EMAIL)

        ppImage.setImageResource(PP_IMAGE)
        userName.setText(USERNAME)
        email.setText(E_MAIL)
    }

    val clickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.back_btn_profile -> {
                onBackPressed()
            }
        }
    }
}