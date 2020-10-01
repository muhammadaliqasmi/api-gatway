package com.qasmi.gateway.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

/**
 * This class enables discovery client for api gateway
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */

@Configuration
@EnableEurekaClient
public class DiscoveryClientConfig {

}
