package com.java.test.health.providers.api.controller;

import com.java.test.health.providers.api.model.HealthProvider;
import com.java.test.health.providers.api.service.HealthProviderService;
import com.java.test.health.providers.api.service.ProviderFinderService;
import com.java.test.health.providers.api.util.ProviderComparator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthProviderController {

    @Autowired
    private HealthProviderService providerService;

    @Autowired
    private ProviderFinderService providerFinderService;

    @GetMapping("/provider/{id}")
    public ResponseEntity<HealthProvider> get(@PathVariable("id") Long providerId) {
        return ResponseEntity.ok(providerService.get(providerId));
    }

    @PostMapping("/provider")
    @ResponseBody
    public ResponseEntity<HealthProvider> create(@RequestBody JSONObject jsonObject) throws JSONException {
        if (providerService.isJsonValid(jsonObject.toString())) {
            providerService.create(jsonObject);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping(value = "/provider/{id}", produces = {"application/json"})
    public ResponseEntity<HealthProvider> update(@PathVariable("id") Long providerId, @RequestBody JSONObject jsonObject) throws JSONException {
        HealthProvider providerFound = providerService.get(providerId);

        if (providerFound != null) {
            providerService.update(providerFound, jsonObject);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
        try {
            providerService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /*
    * Metodo para requisicao, passando latitude e longitude atual, e especialidade desejada
    *
    * Essa função utiliza dados mockados e fícticios, apenas para representação de fluxo e funcionalidades
    * */
    @GetMapping("/obterPrestadoresSaude/{latitude}/{longitude}/{especialidade}")
    public ResponseEntity<List<HealthProvider>> getNearProviders(@PathVariable("latitude") double latitude,
                                                                 @PathVariable("longitude") double longitude,
                                                                 @PathVariable("especialidade") String especialidade) {
        try {
            List<HealthProvider> providers = providerService.getAllFromSpecialization(especialidade);

            if (providers.size() == 0) {
                return ResponseEntity.notFound().build();
            }

            providerFinderService.setCurrentLocation(Double.toString(latitude), Double.toString(longitude));

            providers.forEach(provider -> {
                provider.setDistanceInKm(Double.parseDouble(providerFinderService.getDistanceFromCurrent(
                        Double.toString(provider.getLatitude()), Double.toString(provider.getLongitude()))));
                provider.setDistanceInKm((double) provider.getId());
            });

            providers.sort(new ProviderComparator());

            return ResponseEntity.ok(providers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
