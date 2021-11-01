package com.soonyong.mywebcrawler.config;

import java.io.IOException;
import java.util.Collections;

public class InMemoryConfigReader implements ConfigReader{

    private CrawlConfig crawlConfig;

    public InMemoryConfigReader() {
        this.crawlConfig = new CrawlConfig(Collections.singletonList(CrawlConfig.Target.builder()
                .active(true)
                .title("default")
                .interval(1000000)
                .url("http://www.google.com")
                .targetXPath("html")
                .build()));
    }

    @Override
    public CrawlConfig getCrawlConfig() throws IOException {
        return this.crawlConfig;
    }

    @Override
    public void setCrawlConfig(CrawlConfig crawlConfig) throws IOException {
        this.crawlConfig = crawlConfig;
    }
}
