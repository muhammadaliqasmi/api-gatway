package com.qasmi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class houses the main entry-point to run the
 * application
 *
 * @author Muhammad Ali Qasmi
 */
@SpringBootApplication
public class ApiGatewayApplication {

    /**
     * protected constructor
     */
    ApiGatewayApplication() {
    }

    /**
     * main entry-point
     *
     * @param args for setting environment variables and configuration properties
     */
    public static void main(final String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
