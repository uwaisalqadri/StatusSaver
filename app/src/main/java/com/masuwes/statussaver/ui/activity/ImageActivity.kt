package com.masuwes.statussaver.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.masuwes.statussaver.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        supportActionBar?.run {
            title = resources.getString(R.string.preview)
            setDisplayHomeAsUpEnabled(true)
        }

        val statusPath = intent!!.getStringExtra("ImagePath")

        Glide.with(this)
            .load(statusPath)
            .into(statusImageView)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}