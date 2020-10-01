package com.qasmi.gateway.config;

import org.junit.Test;

import com.qasmi.gateway.base.AbstractIntegrationTest;

/**
 * {@link CorsConfigIntegrationTest} implements test cases to ensure all web request's
 * headers are populated with Cors parameters.
 * 
 * @author Muhammad Ali Qasmi
 */
public class CorsConfigIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void shouldRespondToPreflightRequestForCors() {
        webClient.options() //
                .uri("items/5e1c439b9f0abc437ef55642") //
                .header("Access-Control-Request-Method", "GET") //
                .header("Access-Control-Request-Headers", "origin, x-requested-with") //
                .header("Origin", "http://localhost:" + wiremockPort) //
                .exchange() //
                .expectHeader() //
                .valueEquals("Access-Control-Allow-Method", "*") //
                .expectHeader() //
                .valueEquals("Access-Control-Allow-Origin", "*") //
                .expectStatus() //
                .isNoContent();
    }
}
