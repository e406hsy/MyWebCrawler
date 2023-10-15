package com.soonyong.mywebcrawler.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.soonyong.mywebcrawler.config.manage.ConfigManager;
import com.soonyong.mywebcrawler.config.manage.ConfigManagerImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class ConfigManagerTest {

    ConfigManagerImpl configReader;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        configReader = new ConfigManagerImpl(appContext, "test.json");
    }


    @Test
    public void testGetCrawlConfig() throws IOException {
        configReader.getCrawlConfig();
    }

    @Test
    public void testSetCrawlConfig() throws IOException {
        CrawlConfig.Target target = CrawlConfig.Target.builder()
                .title("my test title")
                .active(false)
                .interval(1000)
                .targetXPath("test")
                .url("http://www.example.com")
                .build();
        this.configReader.addCrawlConfigTarget(target);

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ConfigManager configManager = new ConfigManagerImpl(appContext, "test.json");
        CrawlConfig savedCrawlConfig = configManager.getCrawlConfig();
        assertEquals(1, savedCrawlConfig.getTargets().size());
        assertTrue(savedCrawlConfig.getTargets().contains(target));
    }
}