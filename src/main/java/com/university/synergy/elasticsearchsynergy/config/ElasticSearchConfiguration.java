package com.university.synergy.elasticsearchsynergy.config;



import com.university.synergy.elasticsearchsynergy.config.properties.ElasticProperties;
import com.university.synergy.elasticsearchsynergy.exception.CreateElsticSearchClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.security.KeyStore;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ElasticProperties.class)
public class ElasticSearchConfiguration {

    private final ElasticProperties elasticProperties;

    @Bean
    public RestClient createSimpleElasticClient() throws CreateElsticSearchClientException {
        try (var trustStoreJksFile = new FileInputStream(elasticProperties.getCertPath())){
            final var credentialsProvider = new BasicCredentialsProvider();

            credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(elasticProperties.getUsername(), elasticProperties.getPassword()));

            var trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(trustStoreJksFile, elasticProperties.getCertPassword().toCharArray());

            var sslBuilder = SSLContexts.custom()
                .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy());
            final var sslContext = sslBuilder.build();
            var client = RestClient
                .builder(new HttpHost(elasticProperties.getHost(), elasticProperties.getPort(), elasticProperties.getScheme()))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                    .setSSLContext(sslContext)
                    .setDefaultCredentialsProvider(credentialsProvider))
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(elasticProperties.getConnectionTimeout())
                    .setSocketTimeout(elasticProperties.getSocketTimeout())).build();

            log.info("elasticsearch client created");
            return client;
        } catch (Exception e) {
            log.error("Creation error elastic client {}", e.getMessage());
            throw new CreateElsticSearchClientException("Could not create an elasticsearch client!!");
        }
    }
}
