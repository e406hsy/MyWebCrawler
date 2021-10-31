package com.soonyong.mywebcrawler.config;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigReaderImpl implements ConfigReader {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private CrawlConfig crawlConfig;
    private final Context context;
    private final String configFileName;
    private static final String DEFAULT_CONFIG_FILE_NAME = "crawlConfig.json";

    public ConfigReaderImpl(Context context) throws IOException {
        this(context, DEFAULT_CONFIG_FILE_NAME);
    }

    public ConfigReaderImpl(Context context, String fileName) throws IOException {
        this.context = context;
        this.configFileName = fileName;
        File file = new File(this.context.getFilesDir(), this.configFileName);
        if (!file.exists()) {
            file.createNewFile();
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

    @Override
    public void setCrawlConfig(CrawlConfig crawlConfig) throws IOException {
        this.crawlConfig = crawlConfig;
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
