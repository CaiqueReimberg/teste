package com.java.test.health.providers.api.model;

import com.java.test.health.providers.api.enumerators.MedicalSpecialization;
import lombok.Data;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class HealthProvider {
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double distanceInKm;
    private List<MedicalSpecialization> specializations;

    public HealthProvider(String name) {
        this.id = ThreadLocalRandom.current().nextLong(0, 1000);

        this.name = name;
    }
}


