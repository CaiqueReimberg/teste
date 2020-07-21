package com.java.test.health.providers.api.service;

import com.java.test.health.providers.api.model.HealthProvider;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface HealthProviderService {
    /*List<HealthProvider> getAll();*/

    boolean isJsonValid(String stringJson);

    HealthProvider get(Long id);

    HealthProvider create(JSONObject jsonObject) throws JSONException;

    HealthProvider update(HealthProvider healthProvider, JSONObject jsonObject) throws JSONException;

    List<HealthProvider> getAllFromSpecialization(String specialization);
    void delete(Long id);
}
