package com.soonyong.mywebcrawler.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CrawlConfigTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDeserialize() throws JsonProcessingException {
        // given
        String content = "{" +
                "   \"targets\" : " +
                "   [" +
                "       {" +
                "           \"active\":true," +
                "           \"interval\":1000," +
                "           \"url\":\"https://www.google.com\"," +
                "           \"targetXPath\":\"myXPath\"" +
                "       }" +
                "   ]" +
                "}";

        // when
        CrawlConfig crawlConfig = objectMapper.readValue(content, CrawlConfig.class);

        // then
        assertEquals(1, crawlConfig.getTargets().size());
        CrawlConfig.Target target = crawlConfig.getTargets().get(0);
        assertEquals("https", target.getUrl().getScheme());
        assertEquals("www.google.com", target.getUrl().getHost());
        assertTrue(target.isActive());
        assertEquals(1000, target.getInterval());
        assertEquals("myXPath", target.getTargetXPath());
    }
}