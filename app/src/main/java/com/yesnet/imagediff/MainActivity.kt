package com.yesnet.imagediff

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var differenceChecker: ImageButton
    private lateinit var differenceChecker1: ImageButton

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize bounding box all coordinates
        val x = 560
        val y = 260
        val width = 50
        val height = 50
        //set using data class
        val box = BoundingBox(x,y,width,height)

        differenceChecker = findViewById(R.id.DifferenceChecker1)
        differenceChecker1 = findViewById(R.id.DifferenceChecker2)


        val handleTouch: View.OnTouchListener = object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                val x = event.x.toInt()
                val y = event.y.toInt()
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> Log.i("TAG", "touched down")
                    MotionEvent.ACTION_MOVE -> Log.i("TAG", "moving: ($x, $y)")
                    MotionEvent.ACTION_UP -> Log.i("TAG", "touched up")
                }

                val screenX = event.x.toInt()
                val screenY = event.y.toInt()

                if (screenX > box.x && screenY > box.y && screenX < box.x + box.width  && screenY < box.y + box.height) {


                    val toast1 = Toast.makeText(
                        applicationContext, "Congratulation User Action Correct", Toast.LENGTH_SHORT
                    )
                    toast1.show()

                    val handler = Handler()
                    handler.postDelayed(Runnable { toast1.cancel() }, 500)
                }
                else {
                    val toast2 = Toast.makeText(
                        applicationContext, "User Action Fail", Toast.LENGTH_SHORT
                    )
                    toast2.show()

                    val handler = Handler()
                    handler.postDelayed(Runnable { toast2.cancel() }, 500)
                }
                Log.d("Touch Event","Image  X, Y " + screenX + "," + screenY)
                return true
            }
        }

        differenceChecker1.setOnTouchListener(handleTouch)
        differenceChecker.setOnTouchListener(handleTouch)
    }

}
