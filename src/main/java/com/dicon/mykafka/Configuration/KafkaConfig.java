package com.dicon.mykafka.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dyc
 * @date 2023/05/22
 */

@ConfigurationProperties(prefix = "kafka")
@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers}")
    private String bootStrapServer;




}
