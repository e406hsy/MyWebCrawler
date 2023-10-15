package com.soonyong.mywebcrawler.crawl

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.util.Log
import com.soonyong.mywebcrawler.crawl.CrawlJobService

class SampleBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive: $intent")
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar[Calendar.HOUR_OF_DAY] = 8
            calendar[Calendar.MINUTE] = 0
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val crawlIntent = Intent(context, CrawlJobService::class.java)
            val alarmIntent = PendingIntent.getBroadcast(context, 0, crawlIntent, 0)
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntent)
        }
    }

    companion object {
        private const val TAG = "AlarmReceiver"
    }
}