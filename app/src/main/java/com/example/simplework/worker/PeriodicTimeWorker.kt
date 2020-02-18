package com.example.simplework.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicTimeWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {

        Log.e("Periodic", "PeriodicTimeWorker")

        return Result.success()
    }
}