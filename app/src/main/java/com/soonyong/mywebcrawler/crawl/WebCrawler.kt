package com.soonyong.mywebcrawler.crawl

import android.util.Log
import com.soonyong.mywebcrawler.config.CrawlConfig
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException
import java.util.stream.Collectors

class WebCrawler {
    fun getTexts(target: CrawlConfig.Target): List<String> {
        return try {
            val document = Jsoup.connect(target.url.toString()).get()
            Log.d(javaClass.name, "getTexts: document = $document")
            val elements = document.selectXpath(target.targetXPath)
            elements.stream().map { obj: Element -> obj.text() }.collect(Collectors.toList())
        } catch (e: IOException) {
            Log.w(javaClass.name, "target : $target", e)
            emptyList<String>()
        }
    }
}