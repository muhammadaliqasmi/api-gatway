package com.qasmi.gateway.base;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.MockitoAnnotations.*;
import static org.springframework.http.HttpHeaders.*;

import java.time.Duration;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.qasmi.gateway.wiremock.CaptureStateTransformer;

/**
 * Base class for Spring Unit Tests
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@AutoConfigureWireMock(port = 0)
public abstract class AbstractIntegrationTest extends AbstractTest {

    /**
     * Log variable for all child classes. Uses LoggerFactory.getLogger(getClass()) from
     * slf4j Logging
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${wiremock.server.port}")
    protected int wiremockPort;

    protected WebTestClient webClient;

    @Override
    @Before
    public void before() {
        initMocks(this);
        final String baseUri = "http://localhost:" + wiremockPort;
        stubFor(get(urlPathMatching("/oauth/authorize?.*")) //
                .willReturn(aResponse() //
                        .withStatus(HttpStatus.OK.value()) //
                        .withHeader(CONTENT_TYPE, MediaType.TEXT_HTML_VALUE) //
                        .withBodyFile("login.html") //
                        .withTransformers(CaptureStateTransformer.NAME)));
        stubFor(post(urlEqualTo("/loginSubmit")) //
                .willReturn(aResponse() //
                        .withStatus(HttpStatus.FOUND.value()) //
                        .withHeader(HttpHeaders.LOCATION,
                                baseUri + "/login?code=oauth_code&state=${state-key}") //
                        .withTransformers(CaptureStateTransformer.NAME)));
        stubFor(post(urlEqualTo("/oauth/token")) //
                .willReturn(aResponse() //
                        .withStatus(HttpStatus.OK.value()) //
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //
                        .withBodyFile("get_oauth_token.json")));
        stubFor(get(urlPathEqualTo("/userinfo")) //
                .willReturn(aResponse() //
                        .withStatus(HttpStatus.OK.value()) //
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //
                        .withBodyFile("get_user_info.json")));
        this.webClient = WebTestClient.bindToServer() //
                .responseTimeout(Duration.ofSeconds(10)) //
                .baseUrl(baseUri) //
                .build();
    }
}
