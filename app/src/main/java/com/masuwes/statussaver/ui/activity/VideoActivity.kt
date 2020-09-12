package com.masuwes.statussaver.ui.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.masuwes.statussaver.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        supportActionBar?.run {
            title = resources.getString(R.string.preview)
            setDisplayHomeAsUpEnabled(true)
        }

        val path = intent!!.getStringExtra("videoPath")
        videoView.setVideoURI(Uri.parse(path))

        // create an object of media controller
        val mediaController = MediaController(this)
        // set media controller object for a video view
        videoView.setMediaController(mediaController)
        videoView.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}