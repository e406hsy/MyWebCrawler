package com.soonyong.mywebcrawler.crawl;

import android.util.Log;

import com.soonyong.mywebcrawler.config.CrawlConfig;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WebCrawler {

    public List<String> getTexts(CrawlConfig.Target target) {
        try {
            Document document = Jsoup.connect(target.getUrl().toString()).get();
            Log.d(getClass().getName(), "getTexts: document = " + document);
            Elements elements = document.selectXpath(target.getTargetXPath());
            return elements.stream().map(Element::text).collect(Collectors.toList());
        } catch (IOException e) {
            Log.w(getClass().getName(), "target : " + target, e);
            return Collections.emptyList();
        }
    }
}
