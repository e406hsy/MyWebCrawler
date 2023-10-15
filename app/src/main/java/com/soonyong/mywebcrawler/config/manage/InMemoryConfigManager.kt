package com.soonyong.mywebcrawler.config.manage

import com.soonyong.mywebcrawler.config.CrawlConfig
import java.io.IOException
import java.net.URI

class InMemoryConfigManager : ConfigManager {
    override val crawlConfig: CrawlConfig

    init {
        val targets: MutableList<CrawlConfig.Target> = ArrayList()
        targets.add(CrawlConfig.Target(
                active =true,
                title ="ppomppu",
                interval =1000000,
                url= URI("https://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu"),
                targetXPath="//*[@id=\"revolution_main_table\"]/tbody/tr/td[3]/table/tbody/tr/td[2]/div/a/font"
                ))
        targets.add(CrawlConfig.Target(
                active=true,
                title="default2",
                interval=1000000,
                url=URI("http://www.google.com"),
                targetXPath="html"
                ))
        crawlConfig = CrawlConfig(targets)
    }

    @Throws(IOException::class)
    override fun addCrawlConfigTarget(target: CrawlConfig.Target) {
        crawlConfig.targets.add(target)
    }
}