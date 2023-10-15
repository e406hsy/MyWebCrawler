package com.soonyong.mywebcrawler.config.manage

import android.content.Context
import java.io.IOException

object ConfigManagerFactory {
    private var configManager: ConfigManager? = null
    @Throws(IOException::class)
    fun getConfigManager(context: Context?): ConfigManager? {
        if (configManager == null) {
            configManager = InMemoryConfigManager()
        }
        return configManager
    }
}