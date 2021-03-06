package com.soonyong.mywebcrawler.config.manage;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soonyong.mywebcrawler.config.CrawlConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigManagerImpl implements ConfigManager {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private CrawlConfig crawlConfig;
    private final Context context;
    private final String configFileName;
    private static final String DEFAULT_CONFIG_FILE_NAME = "crawlConfig.json";

    public ConfigManagerImpl(Context context) throws IOException {
        this(context, DEFAULT_CONFIG_FILE_NAME);
    }

    public ConfigManagerImpl(Context context, String fileName) throws IOException {
        this.context = context;
        this.configFileName = fileName;
        File file = new File(this.context.getFilesDir(), this.configFileName);
        if (!file.exists()) {
            file.createNewFile();
            setCrawlConfig(new CrawlConfig());
        }

    }

    @Override
    public CrawlConfig getCrawlConfig() throws IOException {
        if (this.crawlConfig == null) {
            try (FileInputStream fileInputStream = this.context.openFileInput(this.configFileName)) {
                this.crawlConfig = objectMapper.readValue(fileInputStream, CrawlConfig.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return this.crawlConfig;
    }

    private void setCrawlConfig(CrawlConfig crawlConfig) throws IOException {
        this.crawlConfig = crawlConfig;
        saveCrawlConfig();
    }

    @Override
    public void addCrawlConfigTarget(CrawlConfig.Target target) throws IOException {
        this.crawlConfig.getTargets().add(target);
        saveCrawlConfig();
    }

    private void saveCrawlConfig() throws IOException {
        try (FileOutputStream fileOutputStream = this.context.openFileOutput(this.configFileName, Context.MODE_PRIVATE)) {
            objectMapper.writeValue(fileOutputStream, this.crawlConfig);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
