package com.yesnet.imagediff

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yesnet.imagediff.Models.GameModelClass

class NextLevelScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_level_screen)


        val gotoHome = findViewById<Button>(R.id.gotoHomeScreen)
        gotoHome.setOnClickListener{
            val  intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }


        val gotoNextLevelScreen = findViewById<Button>(R.id.nextLevel)
        gotoNextLevelScreen.setOnClickListener{

            finish()
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent (this@NextLevelScreen, LevelScreen::class.java)
        startActivity(intent)
    }





}