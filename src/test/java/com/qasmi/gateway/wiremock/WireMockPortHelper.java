package com.qasmi.gateway.wiremock;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.qasmi.gateway.base.AbstractIntegrationTest;

import wiremock.com.github.jknack.handlebars.Helper;
import wiremock.com.github.jknack.handlebars.Options;

/**
 * This class implements {@link Helper}. It is added to provide wireMock helper for port
 * so it can be accessed in response template
 *
 * @author Sidra Zia
 * @since 1.0
 */
@Component
@SuppressWarnings("rawtypes")
public class WireMockPortHelper implements Helper {

    private Environment env;

    /**
     * Constructor to instantiate WiremockPortConfigurer
     *
     * @param env the spring environment
     */
    @Autowired
    public WireMockPortHelper(final Environment env) {
        this.env = env;
    }

    /**
     * Following method is implemented to provide wireMock port helper, which can be
     * accessed from any context in a template. The property is set in
     * {@link AbstractIntegrationTest}
     */
    @Override
    public String apply(final Object arg0, final Options arg1) throws IOException {
        return env.getProperty("wiremock.server.port");
    }
}
