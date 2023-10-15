package com.soonyong.mywebcrawler.config.manage;

import android.content.Context;

import java.io.IOException;

public class ConfigManagerFactory {

    private static ConfigManager configManager;

    private ConfigManagerFactory() {

    }

    public static ConfigManager getConfigManager(Context context) throws IOException {
        if (ConfigManagerFactory.configManager == null) {
            ConfigManagerFactory.configManager = new InMemoryConfigManager();
        }
        return ConfigManagerFactory.configManager;
    }
}
