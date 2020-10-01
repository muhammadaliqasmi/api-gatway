package com.qasmi.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * Houses cors related configurations
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0
 * @Reference: https://github.com/spring-cloud/spring-cloud-gateway/issues/840#issuecomment-522667819
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter(
            final CorsConfigurationSource corsConfigurationSource) {
        return new CorsWebFilter(corsConfigurationSource);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(
            final CorsConfiguration corsConfiguration) {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = //
                new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", //
                corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

    @Bean
    public CorsConfiguration corsConfiguration() {
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        return config;
    }

}
