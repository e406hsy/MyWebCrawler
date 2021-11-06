package com.soonyong.mywebcrawler.crawl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.soonyong.mywebcrawler.config.CrawlConfig;
import com.soonyong.mywebcrawler.config.manage.ConfigManager;
import com.soonyong.mywebcrawler.config.manage.ConfigManagerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.SneakyThrows;

public class CrawlJobService extends BroadcastReceiver {
    private static final Executor executor = Executors.newCachedThreadPool();

    private String TAG = "MyJobService";

    private final WebCrawler webCrawler;

    public CrawlJobService() {
        this.webCrawler = new WebCrawler();
    }

    @SneakyThrows
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onStartJob: " + intent + " main thread start");
        ConfigManager configManager = ConfigManagerFactory.getConfigManager(context);
        executor.execute(() -> {
                    Log.d(TAG, "onStartJob: " + intent + " child thread job");
                    CrawlConfig.Target target = null;
                    try {
                        target = configManager.getCrawlConfig().getTargets().get(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<String> texts = webCrawler.getTexts(target);
                    Log.d(getClass().getName(), texts.toString());
                }
        );
        Log.d(TAG, "onStartJob: " + intent + " main thread end");
    }
}
