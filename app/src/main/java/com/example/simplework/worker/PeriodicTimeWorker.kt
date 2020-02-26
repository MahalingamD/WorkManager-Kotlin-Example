package com.example.simplework.worker

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicTimeWorker(context: Context, val workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {

        Log.e("Periodic", "PeriodicTimeWorker")

        val aData = workerParams.inputData

        Log.e("User ", "" + aData.getString("username") ?: "1")
        Log.e("Password ", "" + aData.getString("password") ?: "1")

        return Result.success(createOutputData())
    }

    private fun createOutputData(): Data {
        return Data.Builder().putString("Success", "1").build()
    }
}