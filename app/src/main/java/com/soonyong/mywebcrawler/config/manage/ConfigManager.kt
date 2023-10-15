package com.soonyong.mywebcrawler.config.manage

import com.soonyong.mywebcrawler.config.CrawlConfig
import java.io.IOException

interface ConfigManager {
    val crawlConfig: CrawlConfig

    @Throws(IOException::class)
    fun addCrawlConfigTarget(target: CrawlConfig.Target)
}