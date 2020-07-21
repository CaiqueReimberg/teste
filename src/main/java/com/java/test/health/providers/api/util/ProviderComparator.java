package com.java.test.health.providers.api.util;

import com.java.test.health.providers.api.model.HealthProvider;

import java.util.Comparator;

public class ProviderComparator implements Comparator<HealthProvider> {
    @Override
    public int compare(HealthProvider o1, HealthProvider o2) {
        return o1.getDistanceInKm().compareTo(o2.getDistanceInKm());
    }
}