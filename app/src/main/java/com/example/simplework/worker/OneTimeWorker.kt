package com.example.simplework.worker

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class OneTimeWorker(val context: Context, val workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        Log.e("One time Worker", "" + workerParams.tags)

        val aData = workerParams.inputData

        Log.e("User ", "" + aData.getString("username") ?: "1")
        Log.e("Password ", "" + aData.getString("password") ?: "1")

        return Result.failure(createOutputData())
    }

    private fun createOutputData(): Data {
        return Data.Builder().putString("Success", "1").build()
    }

}