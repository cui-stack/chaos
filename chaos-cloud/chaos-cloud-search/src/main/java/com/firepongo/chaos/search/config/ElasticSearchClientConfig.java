package com.firepongo.chaos.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author G.G
 * @date 2020/9/15 15:24
 */
@Configuration
public class ElasticSearchClientConfig {
    @Value("${app.search.host:}")
    private String host;
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, 9200, "http")));
    }
}
