package com.yesnet.imagediff

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yesnet.imagediff.Models.*
import kotlinx.android.synthetic.main.game_level1.*
import kotlinx.android.synthetic.main.item_row.*
import java.io.Serializable


class GameActivity : AppCompatActivity() {

    private lateinit var differenceChecker: ImageButton
    private lateinit var differenceChecker1: ImageButton
    private  var  model: GameModelClass?  = null
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_level1)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            model = intent.getSerializableExtra("model", GameModelClass::class.java)
        }else{
            model = intent.getSerializableExtra("model") as GameModelClass
        }


        differenceChecker = findViewById(R.id.DifferenceChecker1)

        Glide.with(this)
            .load(model?.imageUrl)
            .into(differenceChecker)

        differenceChecker1 = findViewById(R.id.DifferenceChecker2)


        Glide.with(this)
            .load(model?.differenceImageUrl)
            .into(differenceChecker1)

      //  val levelName = intent.getStringExtra("levelName")
      //  val levelUnlocked = intent.getBooleanExtra("levelUnLocked", false)
     //   model = GameModelClass(levelName,levelUnlocked,null,null,null,1,null)


        //FOR DIFFERENCE 1
        //Initialize bounding box all coordinates

       /* val result = arrayListOf<BoundingBox>()
        val x1 = 525
        val y1 = 270
        val width1 = 50
        val height1 = 50
        //set using data class
        val box1 = BoundingBox(x1, y1, width1, height1)
        result.add(box1)
        //FOR DIFFERENCE 2
        //Initialize bounding box all coordinates
        val x2 = 850
        val y2 = 350
        val width2 = 50
        val height2 = 50
        //set using data class
        val box2 = BoundingBox(x2, y2, width2, height2)
        result.add(box2)

        //FOR DIFFERENCE 3
        //Initialize bounding box all coordinates
        val x3 = 15
        val y3 = 960
        val width3 = 50
        val height3 = 50
        //set using data class
        val box3 = BoundingBox(x3, y3, width3, height3)
        result.add(box3)
        //FOR DIFFERENCE 4
        //Initialize bounding box all coordinates
        val x4 = 840
        val y4 = 680
        val width4 = 50
        val height4 = 50
        //set using data class
        val box4 = BoundingBox(x4, y4, width4, height4)
        result.add(box4)
        //FOR DIFFERENCE 5
        //Initialize bounding box all coordinates
        val x5 = 860
        val y5 = 110
        val width5 = 50
        val height5 = 50
        //set using data class
        val box5 = BoundingBox(x5, y5, width5, height5)
        result.add(box5)
*/



        val handleTouch: View.OnTouchListener = object : View.OnTouchListener {
    //        @SuppressLint("SuspiciousIndentation")
            @SuppressLint("SuspiciousIndentation")
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

        run lit@{
            model?.boundingBox?.forEach {

                if (screenX > it.x && screenY > it.y && screenX < it.x + it.width && screenY < it.y + it.height) {
                    it.isPass = true

                    val toast1 = Toast.makeText(
                        applicationContext,
                        "Correct",
                        Toast.LENGTH_SHORT
                    )
                    toast1.show()
                    val handler = Handler()
                    handler.postDelayed(Runnable { toast1.cancel() }, 500)


                    return@lit

                }

            }
        }
                 val found = checkAllBoxPass()
                    if (found == true){

                        val toast1 = Toast.makeText(
                            applicationContext,
                            "Congratulation User Action Correct",
                            Toast.LENGTH_SHORT
                        )
                        toast1.show()
                        successUnlockNextLevel()
                        val handler = Handler()
                        handler.postDelayed(Runnable { toast1.cancel() }, 500)

                        finish()
                    }
                    else
                    {
                        val toast2 = Toast.makeText(
                            applicationContext, "Please find all Difference", Toast.LENGTH_SHORT
                        )
                        toast2.show()

                        val handler = Handler()
                        handler.postDelayed(Runnable { toast2.cancel() }, 500)
                    }
                    Log.d("Touch Event", "Image  X, Y " + screenX + "," + screenY)
                    return true
                }
            }
            differenceChecker1.setOnTouchListener(handleTouch)
            differenceChecker.setOnTouchListener(handleTouch)
        }

    fun checkAllBoxPass():Boolean{

        val check = model?.boundingBox?.firstOrNull { !it.isPass }?.isPass

         if (check != null)
             return check

        return true

    }

//sharedPreferences.edit().putInt("IsUnlocked",2).apply()
    fun successUnlockNextLevel(){
        val sharedPreferences = getSharedPreferences("game", Context.MODE_PRIVATE)
        model?.let { sharedPreferences.edit().putInt("IsUnlocked", it.level).apply() }


    }

}
