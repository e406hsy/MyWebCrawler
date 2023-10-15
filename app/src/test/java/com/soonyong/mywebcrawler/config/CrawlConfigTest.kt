package com.soonyong.mywebcrawler.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class CrawlConfigTest {
    @Test
    @Throws(JsonProcessingException::class)
    fun testDeserialize() {
        // given
        val content = "{" +
                "   \"targets\" : " +
                "   [" +
                "       {" +
                "           \"title\":\"my title\"" +
                "           \"active\":true," +
                "           \"interval\":1000," +
                "           \"url\":\"https://www.google.com\"," +
                "           \"targetXPath\":\"myXPath\"" +
                "       }" +
                "   ]" +
                "}"

        // when
        val crawlConfig = objectMapper.readValue(content, CrawlConfig::class.java)

        // then
        assertEquals(1, crawlConfig.targets.size)
        val target: CrawlConfig.Target = crawlConfig.targets[0]
        assertEquals("https", target.url.scheme)
        assertEquals("www.google.com", target.url.host)
        Assert.assertTrue(target.active)
        assertEquals(1000, target.interval)
        assertEquals("myXPath", target.targetXPath)
        assertEquals("my title", target.title)
    }

    companion object {
        private val objectMapper = ObjectMapper()
    }
}