package com.example.kbk.ui.notifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class NotufyWorker(val context:Context,workerParams:WorkerParameters):Worker(context,workerParams)
{
    override fun doWork(): Result {
        return Result.success()
    }

}