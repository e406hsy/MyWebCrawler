package com.soonyong.mywebcrawler.config;

import android.content.Context;

import java.io.IOException;

public interface ConfigReader {
    CrawlConfig getCrawlConfig() throws IOException;

    void setCrawlConfig(CrawlConfig crawlConfig) throws IOException;
}
