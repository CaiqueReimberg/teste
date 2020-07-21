package com.java.test.health.providers.api.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Component
public class MockServerService {
    private static final String STUBS_FOLDER = "src/main/resources/wiremock";

    /*
    * Implementação do mock para servir de api rest na porta 1089 com jsons mockados
    */
    public MockServerService() {
        WireMockServer server = new WireMockServer(WireMockConfiguration.options().usingFilesUnderClasspath(STUBS_FOLDER)
                .port(8089));

        server.stubFor(any(urlEqualTo("/currentLocation?latitude=1&longitude=2"))
                .withQueryParam("latitude", equalTo("1"))
                .withQueryParam("longitude", equalTo("2"))
                .willReturn(aResponse().withBodyFile("setCurrentLocation.json")));

        server.stubFor(any(urlEqualTo("/getDistance?latitude=1&longitude=2"))
                .withQueryParam("latitude", equalTo("1"))
                .withQueryParam("longitude", equalTo("2"))
                .willReturn(aResponse().withBodyFile(("getDistance.json"))));

        server.start();
    }
}
