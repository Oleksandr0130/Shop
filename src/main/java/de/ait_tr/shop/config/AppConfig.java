package de.ait_tr.shop.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AmazonS3 doClient(DoProperties doProp) {

        // Создание обьекта, который содержит ключи доступа
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                doProp.getAccessKey(),
                doProp.getSecretKey()
        );

        // Создание обьекта с инфо о подключении
        AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration(
                        doProp.getUrl(),
                        doProp.getRegion()
                );

        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(endpointConfiguration);

        return clientBuilder.build();
    }
}
