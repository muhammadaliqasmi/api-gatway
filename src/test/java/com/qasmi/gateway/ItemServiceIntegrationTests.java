package com.qasmi.gateway;

import org.junit.Test;

import com.qasmi.gateway.base.AbstractIntegrationTest;

/**
 * This class holds unit tests for industry-service end points
 *
 * @author Muhammad Ali Qasmi
 */
public class ItemServiceIntegrationTests extends AbstractIntegrationTest {

    @Test
    public void shouldFindAllItems() {
        webClient.get() //
                .uri("/items") //
                .exchange() //
                .expectHeader() //
                .valueEquals("Content-Type", "application/hal+json;charset=UTF-8") //
                .expectStatus() //
                .isOk() //
                .expectBody() //
                .jsonPath("$.page.totalElements").isEqualTo(1) //
                .jsonPath("$.page.size").isEqualTo(20) //
                .jsonPath("$._links.self.href") //
                .isEqualTo("http://localhost:" + wiremockPort + "/items{?page,size,sort}") //
                .jsonPath("$._embedded.items[0]._links.self.href") //
                .isEqualTo("http://localhost:" + wiremockPort
                        + "/items/5e1c439b9f0abc437ef55642") //
                .jsonPath("$._embedded.items[0].name") //
                .isEqualTo("notebook") //
                .jsonPath("$._embedded.items[0].description") //
                .isEqualTo("notebook description");

    }
}
