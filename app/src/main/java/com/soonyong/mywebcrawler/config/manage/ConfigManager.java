package com.soonyong.mywebcrawler.config.manage;

import com.soonyong.mywebcrawler.config.CrawlConfig;

import java.io.IOException;

public interface ConfigManager {
    CrawlConfig getCrawlConfig() throws IOException;

    void addCrawlConfigTarget(CrawlConfig.Target target) throws IOException;
}
