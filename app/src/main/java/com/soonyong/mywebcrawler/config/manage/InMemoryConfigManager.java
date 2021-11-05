package com.soonyong.mywebcrawler.config.manage;

import com.soonyong.mywebcrawler.config.CrawlConfig;

import java.io.IOException;
import java.util.List;

public class InMemoryConfigManager implements ConfigManager {

    private CrawlConfig crawlConfig;

    public InMemoryConfigManager() {
        List<CrawlConfig.Target> targets = new java.util.ArrayList<>();
        targets.add(CrawlConfig.Target.builder()
                .active(true)
                .title("default")
                .interval(1000000)
                .url("http://www.google.com")
                .targetXPath("html")
                .build());
        targets.add(CrawlConfig.Target.builder()
                .active(true)
                .title("default2")
                .interval(1000000)
                .url("http://www.google.com")
                .targetXPath("html")
                .build());
        this.crawlConfig = new CrawlConfig(targets);
    }

    @Override
    public CrawlConfig getCrawlConfig() throws IOException {
        return this.crawlConfig;
    }

    @Override
    public void setCrawlConfig(CrawlConfig crawlConfig) throws IOException {
        this.crawlConfig = crawlConfig;
    }

    @Override
    public void addCrawlConfigTarget(CrawlConfig.Target target) throws IOException {
        this.crawlConfig.getTargets().add(target);
    }
}
