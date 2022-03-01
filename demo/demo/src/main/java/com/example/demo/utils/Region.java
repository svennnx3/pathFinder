package com.example.demo.utils;

import java.util.Set;

public enum Region {
    AMERICAS, ANTARCTIC, AFRICA, ASIA, EUROPE, OCEANIA;

    private static final Set<Region> CONTINENTS = Set.of(AFRICA, ASIA, EUROPE);

    public boolean isConnectedWithRegion(Region region) {
        if (this == region) {
            return true;
        } else {
            return (CONTINENTS.contains(this) && CONTINENTS.contains(region));
        }
    }
}
