package com.java.test.health.providers.api.service;

public interface ProviderFinderService {
    void setCurrentLocation(String latitude, String longitude);

    String getDistanceFromCurrent(String latitude, String longitude);
}
