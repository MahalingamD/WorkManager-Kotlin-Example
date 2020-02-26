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
    lateinit var  mPeroidRequest:PeriodicWorkRequest.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_third
        ) as ActivityThirdBinding

        binding.act = this

        setWrokManager()



    }

    /**
     * TODO
     * call work manager every 15 minutes
     */
    private fun setWrokManager() {
       /* aPeriodicWork = PeriodicWorkRequestBuilder<PeriodicTimeWorker>(
            15,
            TimeUnit.MINUTES,
            10,
            TimeUnit.MINUTES
        ).setConstraints(setConstraint())
            .setInputData(createInputData())
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS)

            .build()*/


        mPeroidRequest=PeriodicWorkRequest.Builder(PeriodicTimeWorker::class.java,15,TimeUnit.MINUTES)
            .setConstraints(setConstraint())
         aPeriodicWork=mPeroidRequest.build()
    }


    private fun setConstraint(): Constraints {

        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // check internet connectivity
            .setRequiresBatteryNotLow(true) // check battery level
            //.setRequiresCharging(true) // check charging mode
            // .setRequiresStorageNotLow(true) // check storage
            // .setRequiresDeviceIdle(false) // check device idle state
            .build()

    }

    fun startTask(aView: View) {
        /*WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("UNIQUE", ExistingPeriodicWorkPolicy.KEEP, aPeriodicWork)*/

        WorkManager.getInstance(this).enqueue(aPeriodicWork)


        WorkManager.getInstance(this).getWorkInfoByIdLiveData(aPeriodicWork.id).observe(this,
            Observer {
                if (it != null) {
                    when (it.state) {

                        WorkInfo.State.SUCCEEDED -> {
                            val myResult = it.outputData.getString("Success")
                            Log.e("Out put", myResult?:"")
                            Log.e("STATUS", "SUCCEEDED")
                        }
                        WorkInfo.State.RUNNING -> {
                            val myResult = it.outputData.getString("Success")
                            Log.e("Out put", myResult?:"")
                            Log.e("STATUS", "RUNNING")
                        }
                        WorkInfo.State.CANCELLED -> {
                            Log.e("STATUS", "CANCELLED")
                        }
                        WorkInfo.State.FAILED -> {
                            Log.e("STATUS", "FAILED")
                        }
                        WorkInfo.State.ENQUEUED -> {
                            val myResult = it.outputData.getString("Success")
                            Log.e("Out put", myResult?:"")
                            Log.e("STATUS", "ENQUEUED")
                        }
                        WorkInfo.State.BLOCKED -> {
                            Log.e("STATUS", "BLOCKED")
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

    private fun createInputData(): Data {

        return Data.Builder()
            .putString("username", "Maha")
            .putString("password", "Lingam").build()


    }


    override fun onDestroy() {
        super.onDestroy()
        WorkManager.getInstance(this).cancelAllWork()
        WorkManager.getInstance(this).cancelUniqueWork("UNIQUE")
    }

}
