package com.java.test.health.providers.api.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProviderFinderServiceImpl implements ProviderFinderService {
    // final String FINDER_URL = "http://localhost:8089";

    private final RestTemplate restTemplate;

    public ProviderFinderServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void setCurrentLocation(String latitude, String longitude) {

    }

    /*
    * Retorno simbolico
    */
    @Override
    public String getDistanceFromCurrent(String latitude, String longitude) {
        /*
        * Neste ponto deveria ser utilizado a criaçao da URL utilizando URL com URIBuilder ou montando como string:
        * String url = FINDER_URL + "/getDistance?latitude=" + latitude + "&longitude=" + longitude;
        *
        * Mas para utilizaçã do mock de maneira simples serã utilizados dados hardcoded e sem influêcia neste código
        *
        * Poderia-se utilizar o mock:
        * String url = FINDER_URL + "/getDistance?latitude=1&longitude=2";
        * Object responseEntity = restTemplate.getForObject(url, Object.class);
        * */

        try {
            /*
            * Chamada para uma api simples, apenas para continuar o fluxo
            */
            Object quote = restTemplate.getForObject(
                    "https://gturnquist-quoters.cfapps.io/api/random", Object.class);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
}
