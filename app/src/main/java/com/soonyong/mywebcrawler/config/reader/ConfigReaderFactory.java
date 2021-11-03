package com.soonyong.mywebcrawler.config.reader;

import android.content.Context;

import java.io.IOException;

public class ConfigReaderFactory {

    private static ConfigReader configReader;

    private ConfigReaderFactory() {

    }

    public static ConfigReader getConfigReader(Context context) throws IOException {
        if (ConfigReaderFactory.configReader == null) {
            ConfigReaderFactory.configReader = new InMemoryConfigReader();
        }
        return ConfigReaderFactory.configReader;
    }
}
