package com.yesnet.imagediff

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yesnet.imagediff.Adapter.GameAdapter
import com.yesnet.imagediff.Models.Box
import com.yesnet.imagediff.Models.GameModel
import com.yesnet.imagediff.Models.GameModelClass
import kotlinx.android.synthetic.main.item_row.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.Serializable
import java.nio.charset.Charset
import java.util.jar.Attributes.Name
import kotlin.properties.Delegates

class LevelScreen : AppCompatActivity(){

    private lateinit var adapter: GameAdapter
    private lateinit var recyclerView: RecyclerView
   // private  var  model: GameModelClass?  = null
   private var levelList: ArrayList<GameModelClass>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        setupLevelAdaptor()


    }

    override fun onResume() {

        if (levelList != null)
        {
            updateLevelList()
            adapter.notifyDataSetChanged()
        }
        super.onResume()
    }

    override fun onPause() {


        super.onPause()
    }

    fun isLevelUnlocked(level: Int): Boolean{
        val sharedPreferences = getSharedPreferences("game",Context.MODE_PRIVATE)
        val unlockLevel = sharedPreferences.getInt("IsUnlocked",-1)

        // unlockLevel = A
        // Level = B

        //  A < B -> Fail = Condition 1
        //  A > B -> Pass = Condition 2
        // A = B    --> Pass = Condition 3
        // B > A    ----> Fail = Condition 4

        // unlockLevel pichale level hoga hamesah
        if ( unlockLevel >= level || level - unlockLevel == 1 ) { // Pass: Condition 2 +  Condition 3
            return  true // ye maine check kar k unlock kia
        }


      /*  if(level == 1 ){
            return  true // ye maine forcefully unlock kia kyn hame level 1 hamesha unlock chye
        }*/

        return false
    }


    fun setupLevelAdaptor(){


        try {


            val jsonString = getJSONFromAssets()
            val gameData = Gson().fromJson(jsonString, GameModel::class.java)

            // set the LayoutManager For this Recyclerview use
            val LayoutManager = LinearLayoutManager(this)
            recyclerView = findViewById(R.id.levelListView)

            recyclerView.layoutManager = LayoutManager


            recyclerView.setHasFixedSize(true)
            adapter = GameAdapter( gameData.game)
            recyclerView.adapter = adapter


            // safe game in new variable and processed upto game levels using loop

            levelList = gameData.game
            updateLevelList()


            adapter.onItemClick = {



                if (it.isUnlocked == true) {

                    val intent1 = Intent(this, GameActivity::class.java)
                    intent1.putExtra("level", it.level)
                    intent1.putExtra("model", it as Serializable)
                    intent1.putExtra("levelName", it.Name)
                    intent1.putExtra("levelUnLocked", it.isUnlocked)
                    startActivity(intent1)



                } else {
                    it.isUnlocked != true
                }


            }
        }
        catch (e: JSONException){

            e.printStackTrace()

        }


    }

    private fun updateLevelList(){
        levelList?.forEach {
            it.isUnlocked = isLevelUnlocked(it.level)

        }
    }

    private fun getJSONFromAssets(): String? {
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("Box.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json

    }


}