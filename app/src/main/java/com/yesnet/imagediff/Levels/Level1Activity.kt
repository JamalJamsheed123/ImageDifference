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
import com.yesnet.imagediff.Models.*


class Level1Activity : AppCompatActivity() {

    private lateinit var differenceChecker: ImageButton
    private lateinit var differenceChecker1: ImageButton

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        //FOR DIFFERENCE 1
        //Initialize bounding box all coordinates
        val x1 = 525
        val y1 = 270
        val width1 = 50
        val height1 = 50
        //set using data class
        val box1 = BoundingBox1(x1,y1,width1,height1)

        //FOR DIFFERENCE 2
        //Initialize bounding box all coordinates
        val x2 = 850
        val y2 = 350
        val width2 = 50
        val height2 = 50
        //set using data class
        val box2 = BoundingBox2(x2,y2,width2,height2)

        //FOR DIFFERENCE 3
        //Initialize bounding box all coordinates
        val x3 = 15
        val y3 = 960
        val width3 = 50
        val height3 = 50
        //set using data class
        val box3 = BoundingBox3(x3,y3,width3,height3)

        //FOR DIFFERENCE 4
        //Initialize bounding box all coordinates
        val x4 = 840
        val y4 = 680
        val width4 = 50
        val height4 = 50
        //set using data class
        val box4 = BoundingBox4(x4,y4,width4,height4)

        //FOR DIFFERENCE 5
        //Initialize bounding box all coordinates
        val x5 = 860
        val y5 = 110
        val width5 = 50
        val height5 = 50
        //set using data class
        val box5 = BoundingBox5(x5,y5,width5,height5)

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

                if (screenX > box1.x1 && screenY > box1.y1 && screenX < box1.x1 + box1.width1  && screenY < box1.y1 + box1.height1) {


                    val toast1 = Toast.makeText(
                        applicationContext, "Congratulation User Action Correct", Toast.LENGTH_SHORT
                    )
                    toast1.show()

                    val handler = Handler()
                    handler.postDelayed(Runnable { toast1.cancel() }, 500)
                }

                if (screenX > box2.x2 && screenY > box2.y2 && screenX < box2.x2 + box2.width2  && screenY < box2.y2 + box2.height2) {


                    val toast2 = Toast.makeText(
                        applicationContext, "Congratulation User Action Correct", Toast.LENGTH_SHORT
                    )
                    toast2.show()

                    val handler = Handler()
                    handler.postDelayed(Runnable { toast2.cancel() }, 500)
                }

                if (screenX > box3.x3 && screenY > box3.y3 && screenX < box3.x3 + box3.width3  && screenY < box3.y3 + box3.height3) {


                    val toast3 = Toast.makeText(
                        applicationContext, "Congratulation User Action Correct", Toast.LENGTH_SHORT
                    )
                    toast3.show()

                    val handler = Handler()
                    handler.postDelayed(Runnable { toast3.cancel() }, 500)
                }

                if (screenX > box4.x4 && screenY > box4.y4 && screenX < box4.x4 + box4.width4  && screenY < box4.y4 + box4.height4) {


                    val toast4 = Toast.makeText(
                        applicationContext, "Congratulation User Action Correct", Toast.LENGTH_SHORT
                    )
                    toast4.show()

                    val handler = Handler()
                    handler.postDelayed(Runnable { toast4.cancel() }, 500)
                }

                if (screenX > box5.x5 && screenY > box5.y5 && screenX < box5.x5 + box5.width5  && screenY < box5.y5 + box5.height5) {


                    val toast5 = Toast.makeText(
                        applicationContext, "Congratulation User Action Correct", Toast.LENGTH_SHORT
                    )
                    toast5.show()

                    val handler = Handler()
                    handler.postDelayed(Runnable { toast5.cancel() }, 500)
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
