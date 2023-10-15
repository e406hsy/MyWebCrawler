package com.soonyong.mywebcrawler.config.manage

import android.content.Context
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.soonyong.mywebcrawler.config.CrawlConfig
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class ConfigManagerImpl @JvmOverloads constructor(private val context: Context, private val configFileName: String = DEFAULT_CONFIG_FILE_NAME) : ConfigManager {
    private val objectMapper = jacksonObjectMapper()
    override var crawlConfig: CrawlConfig = CrawlConfig()
        set(value) {
            field = value
            saveCrawlConfig()
        }

    init {
        val file = File(context.filesDir, configFileName)
        if (!file.exists()) {
            file.createNewFile()
            this.crawlConfig =CrawlConfig()
        } else {
            file.reader().use {
                this.crawlConfig = objectMapper.readValue(it, CrawlConfig::class.java)
            }
        }
    }

    @Throws(IOException::class)
    override fun addCrawlConfigTarget(target: CrawlConfig.Target) {
        crawlConfig.targets.add(target)
        saveCrawlConfig()
    }

    @Throws(IOException::class)
    private fun saveCrawlConfig() {
        try {
            context.openFileOutput(configFileName, Context.MODE_PRIVATE).use { fileOutputStream -> objectMapper.writeValue(fileOutputStream, crawlConfig) }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            throw e
        }
    }

    companion object {
        private const val DEFAULT_CONFIG_FILE_NAME = "crawlConfig.json"
    }
}