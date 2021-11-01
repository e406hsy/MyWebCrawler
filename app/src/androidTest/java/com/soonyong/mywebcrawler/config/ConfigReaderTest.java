package com.soonyong.mywebcrawler.config;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ConfigReaderTest {

    ConfigReaderImpl configReader;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        configReader = new ConfigReaderImpl(appContext, "test.json");
    }


    @Test
    public void testGetCrawlConfig() throws IOException {
        configReader.getCrawlConfig();
    }

    @Test
    public void testSetCrawlConfig() throws IOException {
        CrawlConfig crawlConfig = new CrawlConfig(List.of(CrawlConfig.Target.builder()
                .title("my test title")
                .active(false)
                .interval(1000)
                .targetXPath("test")
                .url("http://www.example.com")
                .build()));
        this.configReader.setCrawlConfig(crawlConfig);

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ConfigReader configReader = new ConfigReaderImpl(appContext, "test.json");
        CrawlConfig savedCrawlConfig = configReader.getCrawlConfig();
        assertEquals(savedCrawlConfig.getTargets().size(), crawlConfig.getTargets().size());
        assertTrue(savedCrawlConfig.getTargets().containsAll(crawlConfig.getTargets()));
    }
}