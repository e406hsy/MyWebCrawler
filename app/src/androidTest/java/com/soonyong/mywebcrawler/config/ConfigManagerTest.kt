package com.soonyong.mywebcrawler.config

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.soonyong.mywebcrawler.config.manage.ConfigManager
import com.soonyong.mywebcrawler.config.manage.ConfigManagerImpl
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.net.URI

@RunWith(AndroidJUnit4::class)
class ConfigManagerTest {
    var configReader: ConfigManagerImpl? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        configReader = ConfigManagerImpl(appContext, "test.json")
    }

    @Test
    @Throws(IOException::class)
    fun testGetCrawlConfig() {
        configReader!!.crawlConfig
    }

    @Test
    @Throws(IOException::class)
    fun testSetCrawlConfig() {
        val target = CrawlConfig.Target(
                title="my test title",
                active=false,
                interval=1000,
                targetXPath="test",
                url= URI("http://www.example.com"))
        configReader!!.addCrawlConfigTarget(target)
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val configManager: ConfigManager = ConfigManagerImpl(appContext, "test.json")
        val savedCrawlConfig = configManager.crawlConfig
        assertEquals(1, savedCrawlConfig.targets.size)
        Assert.assertTrue(savedCrawlConfig.targets.contains(target))
    }
}