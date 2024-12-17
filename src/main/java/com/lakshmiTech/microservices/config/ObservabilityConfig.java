package com.lakshmiTech.microservices.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class ObservabilityConfig {

    private final ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;

    public ObservabilityConfig(ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory) {
        this.concurrentKafkaListenerContainerFactory = concurrentKafkaListenerContainerFactory;
    }

    public void setObservationForKafkaTemplate() {
        concurrentKafkaListenerContainerFactory.getContainerProperties().setObservationEnabled(true);
    }

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }

    @Bean
    public ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }
}
