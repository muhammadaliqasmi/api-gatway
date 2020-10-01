package com.qasmi.gateway.config;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.springframework.web.cors.CorsConfiguration;

import com.qasmi.gateway.base.AbstractTest;

/**
 * {@link CorsConfigurationTest} implements test cases to ensure beans related to cors
 * configuration are configured and available in application context.
 * 
 * @author Muhammad Ali Qasmi
 */
public class CorsConfigTest extends AbstractTest {

    @Test
    public void shouldContainsCorsConfiguration() {
        assertThat(context.containsBean("corsConfiguration"), is(Boolean.TRUE));
        final CorsConfiguration corsConfiguration = context.getBean("corsConfiguration",
                CorsConfiguration.class);
        assertThat(corsConfiguration.getAllowedMethods(), contains("*"));
        assertThat(corsConfiguration.getAllowedOrigins(), contains("*"));
        assertThat(corsConfiguration.getAllowedHeaders(), contains("*"));
    }

    @Test
    public void shouldContainsCorsConfigurationSource() {
        assertThat(context.containsBean("corsConfigurationSource"), is(Boolean.TRUE));
    }

    @Test
    public void shouldContainsCorsWebFilter() {
        assertThat(context.containsBean("corsFilter"), is(Boolean.TRUE));
    }
}
