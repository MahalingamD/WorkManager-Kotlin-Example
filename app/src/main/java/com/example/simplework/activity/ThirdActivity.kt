package com.example.simplework.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.simplework.R
import com.example.simplework.databinding.ActivityMainBinding
import com.example.simplework.databinding.ActivityThirdBinding
import com.example.simplework.worker.PeriodicTimeWorker
import java.util.concurrent.TimeUnit

class ThirdActivity : AppCompatActivity() {

    lateinit var binding: ActivityThirdBinding
    lateinit var aPeriodicWork: PeriodicWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_third
        ) as ActivityThirdBinding

        binding.act = this



        aPeriodicWork = PeriodicWorkRequestBuilder<PeriodicTimeWorker>(
            15,
            TimeUnit.MINUTES,
            10,
            TimeUnit.MINUTES
        ).setConstraints(setConstraint()).build()

    }


    private fun setConstraint(): Constraints {

        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // check internet connectivity
            .setRequiresBatteryNotLow(true) // check battery level
            .setRequiresCharging(true) // check charging mode
            // .setRequiresStorageNotLow(true) // check storage
            // .setRequiresDeviceIdle(false) // check device idle state
            .build()

    }

    fun startTask(aView: View) {
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("UNIQUE", ExistingPeriodicWorkPolicy.REPLACE, aPeriodicWork)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(aPeriodicWork.id).observe(this,
            Observer {
                if (it != null) {
                    when (it.state) {

                        WorkInfo.State.SUCCEEDED -> {
                            //val myResult = it.outputData.getString("Success")
                           // Log.e("Out put", myResult)
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


    }

    fun cancelTask(aView: View) {
        WorkManager.getInstance(this).cancelUniqueWork("UNIQUE")
    }

}
