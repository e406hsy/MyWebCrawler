package com.soonyong.mywebcrawler.config

import java.net.URI

data class CrawlConfig(val targets: MutableList<Target> = mutableListOf()) {


    data class Target(val title: String, val active: Boolean = false, val interval: Long = 0, val url: URI, val targetXPath: String)
}