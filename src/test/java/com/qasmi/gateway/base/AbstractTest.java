package com.qasmi.gateway.base;

import static org.mockito.MockitoAnnotations.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link AbstractTest} implements all required configuration and application context to
 * execute unit tests.
 *
 * @author Muhammad Ali Qasmi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractTest {

    /**
     * Log variable for all child classes. Uses LoggerFactory.getLogger(getClass()) from
     * slf4j Logging
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ApplicationContext context;

    /**
     * {@inheritDoc}
     */
    @Before
    public void before() {
        initMocks(this);
    }
}
