package com.ilham.dicoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    private lateinit var rvPortofolio: RecyclerView
    private lateinit var greeting: TextView
    private lateinit var profileButton: CircleImageView

    private var list: ArrayList<portofolio> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        profileButton = findViewById(R.id.ppImage)
        profileButton.setOnClickListener(clickListener)


        rvPortofolio = findViewById(R.id.rv_portofolio)
        rvPortofolio.setHasFixedSize(true)
        profileButton.setImageResource(userData.getUserData[0].photo)

        showUsername()

        list.addAll(portofolioData.listData)
        showRecyclerCardview()

    }

    private fun showUsername() {
        var userN: String = userData.getUserData[0].username
        var indexofspace: Int = userData.getUserData[0].username.indexOf(' ')
        var user: String? = ""
        user = userN.substring(0, indexofspace)
        greeting = findViewById(R.id.greeting)
        greeting.setText("Halo " + user + " !")
    }

    val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.ppImage -> {
                val ppActivity = Intent(this, profileActivity::class.java)
                ppActivity.putExtra(profileActivity.PROFILE_IMAGE, userData.getUserData[0].photo)
                ppActivity.putExtra(profileActivity.USER, userData.getUserData[0].username)
                ppActivity.putExtra(profileActivity.EMAIL, userData.getUserData[0].email)
                startActivity(ppActivity)
            }
        }
    }
    private fun showRecyclerCardview() {
        rvPortofolio.layoutManager = LinearLayoutManager(this)
        val cardViewAdapter = cardMemberAdapter(list)
        rvPortofolio.adapter = cardViewAdapter
    }
}