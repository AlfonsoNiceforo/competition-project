package com.alfonso_niceforo.game_service.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    NewTopic characterVerifiedTopic() {
        return TopicBuilder
                .name("player-verified-topic")
                .build();
    }
}
