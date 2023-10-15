package com.soonyong.mywebcrawler.crawl

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.soonyong.mywebcrawler.config.CrawlConfig
import com.soonyong.mywebcrawler.config.manage.ConfigManagerFactory

import java.io.IOException
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class CrawlJobService : BroadcastReceiver() {
    private val webCrawler: WebCrawler = WebCrawler()

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onStartJob: $intent main thread start")
        val configManager = ConfigManagerFactory.getConfigManager(context)
        executor.execute {
            Log.d(TAG, "onStartJob: $intent child thread job")
            var target: CrawlConfig.Target? = null
            try {
                target = configManager!!.crawlConfig.targets[0]
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val texts = target?.let { webCrawler.getTexts(it) }
            Log.d(javaClass.name, texts.toString())
        }
        Log.d(TAG, "onStartJob: $intent main thread end")
    }

    companion object {
        private const val TAG = "MyJobService"
        private val executor: Executor = Executors.newCachedThreadPool()
    }
}