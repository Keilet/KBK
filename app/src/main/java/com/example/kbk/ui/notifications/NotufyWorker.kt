package com.example.kbk.ui.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.kbk.NOTIFICATION_CHANNEL_ID
import com.example.kbk.R
import com.example.kbk.activities.Bnv
import com.example.kbk.activities.MainActivity
import com.example.kbk.model.KBKMessage
import com.example.kbk.model.KBKMessages
import com.example.kbk.network.Api
import com.example.kbk.network.RetrofitClient


class NotufyWorker(val context:Context,workerParams:WorkerParameters):Worker(context,workerParams)
{
    override fun doWork(): Result {
        val query=QueryPreferences.getStoredQuery(context)
        val lastResultId=QueryPreferences.getLastResultId(context)
        var items:ArrayList<KBKMessage> = arrayListOf()
        if (query.isEmpty()){
            items= RetrofitClient().allMessegeRequest().execute().body()?.messages!!
        }
        if(items.size==0)
        {
            return Result.success()
        }
        var resultId=items.first().id
        if(resultId!=lastResultId)
        {
            QueryPreferences.setLastResultId(context, resultId)

            val intent = Bnv.newIntent(context)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val resources = context.resources
            val notification = NotificationCompat
                .Builder(context, NOTIFICATION_CHANNEL_ID)
                .setTicker(resources.getString(R.string.new_message_title))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(resources.getString(R.string.new_message_title))
                .setContentText(resources.getString(R.string.new_message_text))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        }
        return Result.success()
    }
    private fun showBackgroundNotification(
        requestCode: Int,
        notification: Notification
    ) {
        val intent = Intent(ACTION_SHOW_NOTIFICATION).apply {
            putExtra(REQUEST_CODE, requestCode)
            putExtra(NOTIFICATION, notification)
        }

        context.sendOrderedBroadcast(intent, PERM_PRIVATE)
    }

    companion object {
        const val ACTION_SHOW_NOTIFICATION =
            "com.example.kbk.SHOW_NOTIFICATION"
        const val PERM_PRIVATE = "com.example.kbk.PRIVATE"
        const val REQUEST_CODE = "REQUEST_CODE"
        const val NOTIFICATION = "NOTIFICATION"
    }
}