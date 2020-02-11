package com.example.simplework.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.simplework.R
import com.example.simplework.worker.OneTimeWorker
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_first.*
import java.util.concurrent.TimeUnit


class FirstActivity : AppCompatActivity() {

    private var mUserName: TextInputEditText? = null
    private var mPassword: TextInputEditText? = null
    private var mLoginButton: MaterialButton? = null
    lateinit var aOneTimeSync: OneTimeWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        init()
    }


    private fun init() {

        mUserName = findViewById(R.id.login_user_edittext)
        mPassword = findViewById(R.id.login_password_edittext)
        mLoginButton = findViewById(R.id.login_button)


        mLoginButton!!.setOnClickListener {


            if (validateData()) {
                //Call
                aOneTimeSync = OneTimeWorkRequest.Builder(OneTimeWorker::class.java)
                    .setConstraints(setConstraint()) //set Constraint
                    .addTag("After Click") //add tag
                    .setInitialDelay(10000L, TimeUnit.MILLISECONDS) //10 seconds Initial delay
                    .setInputData(createInputData()) //set input data
                    .build()
                WorkManager.getInstance(this).enqueue(aOneTimeSync)

                //Get WorkManager status
                WorkManager.getInstance(this).getWorkInfoByIdLiveData(aOneTimeSync.id).observe(this,
                    Observer {
                        if (it != null) {
                            when (it.state) {

                                WorkInfo.State.SUCCEEDED -> {
                                    val myResult = it.outputData.getString("Success")
                                    Log.e("Out put", myResult)
                                    Log.e("STATUS", "SUCCEEDED")
                                }

                                WorkInfo.State.RUNNING -> {
                                    Log.e("STATUS", "RUNNING")
                                }

                                WorkInfo.State.CANCELLED -> {
                                    Log.e("STATUS", "CANCELLED")
                                }

                                WorkInfo.State.FAILED -> {
                                    Log.e("STATUS", "FAILED")
                                }
                                else -> {
                                    Log.e("STATUS", "STATUS")
                                }
                            }
                        }

                    })
            }else{
                Toast.makeText(this,"Enter value",Toast.LENGTH_SHORT).show()
            }

        }

        cancel_button.setOnClickListener {
            //Cancel WorkManager
            WorkManager.getInstance(this).cancelWorkById(aOneTimeSync.id)
        }

    }

    private fun validateData(): Boolean {

        val aBool = if (mUserName!!.text.toString().isEmpty()) {
            false
        } else !mPassword!!.text.toString().isEmpty()

        return aBool
    }

    private fun createInputData(): Data {

        return Data.Builder()
            .putString("username", mUserName!!.text.toString())
            .putString("password", mPassword!!.text.toString()).build()
    }


    private fun setConstraint(): Constraints {

        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // check internet connectivity
            //.setRequiresBatteryNotLow(true) // check battery level
            // .setRequiresCharging(true) // check charging mode
            // .setRequiresStorageNotLow(true) // check storage
            // .setRequiresDeviceIdle(false) // check device idle state
            .build()

    }


}
