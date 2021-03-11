package com.ilham.dicoding

object userData {
    private val name = arrayOf(
        "Ilham Hadisyah"
    )
    private val email = arrayOf(
        "a2582414@bangkit.academy"
    )
    private val photo = intArrayOf(
        R.drawable.profile
    )

    val getUserData: ArrayList<user>
        get() {
            val list = arrayListOf<user>()
            for (position in name.indices) {
                val usr = user()
                usr.username = userData.name[position]
                usr.email = userData.email[position]
                usr.photo = userData.photo[position]
                list.add(usr)
            }
            return list
        }
}