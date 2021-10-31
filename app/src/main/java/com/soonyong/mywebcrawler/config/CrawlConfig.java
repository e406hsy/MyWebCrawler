package com.soonyong.mywebcrawler.config;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CrawlConfig {

    private List<Target> targets;

    public CrawlConfig() {
        this(Collections.emptyList());
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @EqualsAndHashCode
    public static class Target {

        public static TargetBuilder builder() {
            return new TargetBuilder();
        }

        private String title;
        private boolean active;
        private long interval;
        private URI url;
        private String targetXPath;

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class TargetBuilder {
            private String title;
            private boolean active;
            private long interval;
            private String url;
            private String targetXPath;

            public TargetBuilder title(String title) {
                this.title = title;
                return this;
            }

            public TargetBuilder active(boolean active) {
                this.active = active;
                return this;
            }

            public TargetBuilder interval(long interval) {
                this.interval = interval;
                return this;
            }

            public TargetBuilder url(String url) {
                this.url = url;
                return this;
            }

            public TargetBuilder targetXPath(String targetXPath) {
                this.targetXPath = targetXPath;
                return this;
            }

            public Target build() {
                Target target = new Target();
                target.title = this.title;
                target.active = this.active;
                target.interval = this.interval;
                target.targetXPath = this.targetXPath;
                target.setUrl(this.url);
                return target;
            }
        }

        @JsonSetter
        public void setUrl(String url) {
            this.url = URI.create(url);
        }
    }
}
