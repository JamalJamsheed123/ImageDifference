package com.yesnet.imagediff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startbutton = findViewById<Button>(R.id.StartGame)
        startbutton.setOnClickListener{
            val  intent = Intent(this, LevelScreen::class.java)
            startActivity(intent)
        }

        removeActionBar()
    }

    private fun removeActionBar(){

        supportActionBar?.hide()
    }
}