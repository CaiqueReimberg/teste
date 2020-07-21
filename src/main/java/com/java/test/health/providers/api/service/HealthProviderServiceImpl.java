package com.java.test.health.providers.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.test.health.providers.api.enumerators.MedicalSpecialization;
import com.java.test.health.providers.api.model.HealthProvider;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class HealthProviderServiceImpl implements HealthProviderService{
    List<HealthProvider> providers;

    private HealthProviderServiceImpl() {
        providers = new ArrayList<>();
        createProviders();
    }

    /*
    * Método utilizado apenas para popular de prestadores de saude com dados mockados apenas representativos
    */
    private void createProviders() {
        for (int i =0; i < 20; i++) {
            List<MedicalSpecialization> specializationList = new ArrayList<>();

            specializationList.add((MedicalSpecialization.values()[ThreadLocalRandom.current()
                    .nextInt(0, MedicalSpecialization.values().length)]));
            specializationList.add((MedicalSpecialization.values()[ThreadLocalRandom.current()
                    .nextInt(0, MedicalSpecialization.values().length)]));

            HealthProvider healthProvider = new HealthProvider("Nome" + i);

            healthProvider.setSpecializations(specializationList);
            healthProvider.setAddress("Endereço" + i);

            /*
            * Utilizando nextInt() apenas para pegar dados inteiros
            */
            healthProvider.setLatitude((double)ThreadLocalRandom.current().nextInt());
            healthProvider.setLongitude((double)ThreadLocalRandom.current().nextInt());

            providers.add(healthProvider);
        }

    }

    public boolean isJsonValid(String stringJson) {
        try {
            return new ObjectMapper().readTree(stringJson) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private Long parseId(JSONObject healthProvider) throws JSONException {
        return (long) healthProvider.get("id");
    }

    private List<MedicalSpecialization> parseProviderSpecializations(JSONObject jsonObject) throws JSONException {
        return (List<MedicalSpecialization>) jsonObject.getJSONArray("specializations");
    }

    private String parseName(JSONObject jsonObject) throws JSONException {
        return (String) jsonObject.get("name");
    }

    private void updateProviderValues(HealthProvider healthProvider, JSONObject jsonObject) throws JSONException {
        healthProvider.setName(parseName(jsonObject));
        healthProvider.setSpecializations(parseProviderSpecializations(jsonObject));
    }


    @Override
    public HealthProvider get(Long id) {
        return providers.stream().filter(provider -> id.equals(provider.getId())).collect(Collectors.toList()).get(0);
    }

    @Override
    public HealthProvider create(JSONObject jsonObject) throws JSONException {
        HealthProvider healthProvider = new HealthProvider(parseName(jsonObject));
        healthProvider.setId(parseId(jsonObject));
        healthProvider.setSpecializations(parseProviderSpecializations(jsonObject));

        providers.add(healthProvider);

        return healthProvider;
    }

    @Override
    public HealthProvider update(HealthProvider healthProvider, JSONObject jsonObject) throws JSONException {
        updateProviderValues(healthProvider, jsonObject);

        return healthProvider;
    }

    @Override
    public void delete(Long id) {
        providers.removeIf(provider -> provider.getId().equals(id));
    }

    @Override
    public List<HealthProvider> getAllFromSpecialization(String specialization) {
        List<HealthProvider> availableProviders = new ArrayList<>();
        MedicalSpecialization medicalSpecialization = MedicalSpecialization.valueOf(specialization);

        providers.forEach(healthProvider -> {
            healthProvider.getSpecializations().forEach(currentSpecialization -> {
                if (currentSpecialization == medicalSpecialization) {
                    availableProviders.add(healthProvider);
                }
            });
        });

        return availableProviders;
    }
}
