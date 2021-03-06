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
                .title("ppomppu")
                .interval(1000000)
                .url("https://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu")
                .targetXPath("//*[@id=\"revolution_main_table\"]/tbody/tr/td[3]/table/tbody/tr/td[2]/div/a/font")
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
    public void addCrawlConfigTarget(CrawlConfig.Target target) throws IOException {
        this.crawlConfig.getTargets().add(target);
    }
}
