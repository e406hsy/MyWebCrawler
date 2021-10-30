package com.soonyong.mywebcrawler.config;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ConfigReader {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private CrawlConfig crawlConfig;
    private static final String CONFIG_FILE_NAME = "crawlConfig.json";

    public ConfigReader(Context context) throws IOException {
        if (!Arrays.asList(context.fileList()).contains(CONFIG_FILE_NAME)) {
            File file = new File(context.getFilesDir(), CONFIG_FILE_NAME);
            file.createNewFile();
        }
    }

    public CrawlConfig getCrawlConfig() {
        if (crawlConfig == null) {
            // TODO: read config
        }
        return crawlConfig;
    }

    public void setCrawlConfig(CrawlConfig crawlConfig, Context context) {
        this.crawlConfig = crawlConfig;
        saveCrawlConfig(context);
    }

    private void saveCrawlConfig(Context context) {
        try (FileOutputStream fileOutputStream = context.openFileOutput(CONFIG_FILE_NAME, Context.MODE_PRIVATE)) {
            // TODO: save config
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
