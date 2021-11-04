package com.soonyong.mywebcrawler.config.reader;

import com.soonyong.mywebcrawler.config.CrawlConfig;

import java.io.IOException;

public interface ConfigReader {
    CrawlConfig getCrawlConfig() throws IOException;

    void setCrawlConfig(CrawlConfig crawlConfig) throws IOException;

    void addCrawlConfigTarget(CrawlConfig.Target target) throws IOException;
}
