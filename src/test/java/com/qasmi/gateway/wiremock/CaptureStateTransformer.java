/*
 * %%Ignore-License
 */

package com.qasmi.gateway.wiremock;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

/**
 * This class is a wiremock extension that examines requests on the two stubs tagged with
 * CaptureStateTransformer. If there is a state parameter among the request parameters (as
 * there is in the request to /oauth/authorize), that value is stored. If there is a
 * Location header (as there is in the redirect to /login), it replaces the variable
 * ${state-key} with the stored value. This allows us to capture the state key in the
 * original request for the login form and pass it back as part of the redirect after the
 * login form is submitted.
 *
 * @author Brian Watkins
 * @see https://engineering.pivotal.io/post/faking_oauth_sso/
 */
@Component
public class CaptureStateTransformer extends ResponseDefinitionTransformer {

    public static final String NAME = "CaptureStateTransformer";

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDefinition transform(final Request request,
            final ResponseDefinition responseDef, final FileSource files,
            final Parameters parameters) {
        final String stateParamKey = "state";
        String state = null;
        // Capture the state parameter from the /oauth/authorize request
        if (request.queryParameter(stateParamKey) != null) {
            state = request.queryParameter(stateParamKey).firstValue();
        }

        // Add the state parameter to the /login redirect
        if (responseDef.getHeaders().getHeader(HttpHeaders.LOCATION).isPresent()) {
            final String redirectLocation = responseDef.getHeaders().getHeader(
                    HttpHeaders.LOCATION).firstValue();
            return ResponseDefinition //
                    .redirectTo(redirectLocation.replace("${state-key}", state));
        }
        return responseDef;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return CaptureStateTransformer.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyGlobally() {
        return false;
    }

}
