package com.qasmi.gateway.wiremock;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.WireMockConfigurationCustomizer;
import org.springframework.stereotype.Component;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

import wiremock.com.github.jknack.handlebars.Helper;

/**
 * This class implements {@link WireMockConfigurationCustomizer}. It is added to customize
 * and extend wireMock configurations
 *
 * @author Sidra Zia
 * @since 1.0
 */
@Component
public class WireMockCustomizer implements WireMockConfigurationCustomizer {

    private final WireMockPortHelper wireMockHelper;

    /**
     * Constructor to instantiate WireMockCustomizer
     *
     * @param wireMockHelper the {@link WireMockPortHelper} object
     */
    @Autowired
    public WireMockCustomizer(final WireMockPortHelper wireMockHelper) {
        this.wireMockHelper = wireMockHelper;
    }

    /**
     * Method implemented to allow customization in WireMock configuration. Here we have
     * set wireMockPort to be accessible in response template
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void customize(final WireMockConfiguration options) {
        final Map<String, Helper> helpers = new HashMap<String, Helper>();
        helpers.put("wireMockPort", wireMockHelper);
        options.extensions(new ResponseTemplateTransformer(false, helpers));
    }
}
