package com.example.simplework.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import com.example.simplework.R
import com.example.simplework.databinding.ActivityMainBinding
import com.example.simplework.model.Taskinfo
import com.example.simplework.utils.Logger
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    val mTaskList = ArrayList<Taskinfo>()

    lateinit var rocketAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        ) as ActivityMainBinding

        binding.act = this


        val rocketImage = findViewById<ImageView>(R.id.image1).apply {
            setBackgroundResource(R.drawable.shake_anim)
            rocketAnimation = background as AnimationDrawable
        }


        val quotient = 285 / 60
        val remainder = 285 % 60

        val aFinalSleepHrs= "$quotient.${remainder.toString()[0]}"

       // Log.e("Final string",aFinalSleepHrs)

        Logger().e("Final string",aFinalSleepHrs)

       // init()
    }

    private fun init() {

        for (i in 0 until 5) {
            val aTaskinfo = Taskinfo()

            aTaskinfo.TaskName = "Task" + i
            aTaskinfo.TaskType = "$i"

            aTaskinfo.TastEndDate = "${i}0-02-2020"

            if (i == 4) {
                aTaskinfo.TaskStartDate = "0${i - 1}-02-2020"
            } else {
                aTaskinfo.TaskStartDate = "0$i-02-2020"
            }

            mTaskList.add(aTaskinfo)
        }

        val aList = mTaskList.sortedWith(compareByDescending { it.TaskStartDate })

        val aTask = aList.groupBy { it.TaskStartDate } as LinkedHashMap

        aTask.forEach {
            it.value.forEach {

                Log.e("Task", it.TaskName.toString())
            }
        }

        print("Task" + aTask.toString())
    }

    fun onSave(view: View) {
        startActivity(Intent(this, FirstActivity::class.java))
    }

    fun button2Click(view: View) {
        Logger().e("Final string","button2Click")

        startActivity(Intent(this, ThirdActivity::class.java))
          //view.button2.setBackgroundResource(R.drawable.shake_anim)
        //rocketAnimation.start()
    }

    fun button3Click(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }


    fun button4Click(view: View) {
        startActivity(Intent(this, FourthActivity::class.java))
    }


}
