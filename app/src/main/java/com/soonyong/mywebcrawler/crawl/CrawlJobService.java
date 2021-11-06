package com.soonyong.mywebcrawler.crawl;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.soonyong.mywebcrawler.config.CrawlConfig;
import com.soonyong.mywebcrawler.config.manage.ConfigManager;
import com.soonyong.mywebcrawler.config.manage.ConfigManagerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.SneakyThrows;

public class CrawlJobService extends JobService {
    private static final Executor executor = Executors.newCachedThreadPool();

    private String TAG = "MyJobService";

    private final WebCrawler webCrawler;

    public CrawlJobService() {
        this.webCrawler = new WebCrawler();
    }

    @SneakyThrows
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: " + params.getJobId() + " main thread start");
        ConfigManager configManager = ConfigManagerFactory.getConfigManager(getApplicationContext());
        executor.execute(() -> {
                    Log.d(TAG, "onStartJob: " + params.getJobId() + " child thread job");
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
        Log.d(TAG, "onStartJob: " + params.getJobId() + " main thread end");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: " + params.getJobId());
        return false;
    }
}
