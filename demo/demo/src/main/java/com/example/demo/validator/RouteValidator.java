package com.example.demo.validator;

import com.example.demo.exception.PathNotFoundException;
import com.example.demo.model.Country;

import java.util.Map;
import java.util.Optional;

public class RouteValidator {
    private final Map<String, Country> countryMap;

    public RouteValidator(Map<String, Country> countryMap) {
        this.countryMap = countryMap;
    }

    public void verifyThatRouteIsPossibleToFind(Country origin, Country destination) {
        checkDoesDestinationIsTheSameAsOrigin(origin, destination);
        checkLandConnectionBetween(origin, destination);
        checkCountriesIsolation(origin, destination);
    }

    public Country checkDoesCountryIsInList(String countryName) {
        return Optional.ofNullable(countryMap.get(countryName)).orElseThrow(() ->
                new PathNotFoundException("Country named " + countryName + " is not in the Country List get from API"));
    }

    private void checkLandConnectionBetween(Country origin, Country destination) {
        if (!origin.getRegion().isConnectedWithRegion(destination.getRegion())) {
            throw new PathNotFoundException(origin.getName() + " is not connected by land with country " + destination.getName());
        }
    }

    private void checkCountriesIsolation(Country origin, Country destination) {
        checkDoesCountryHasBorders(origin);
        checkDoesCountryHasBorders(destination);
    }

    private void checkDoesCountryHasBorders(Country country) {
        if (country.getBorders().isEmpty()) {
            throw new PathNotFoundException(country.getName() + " has no borders");
        }
    }

    private void checkDoesDestinationIsTheSameAsOrigin(Country origin, Country destination) {
        if (origin.getName().equalsIgnoreCase(destination.getName())) {
            throw new PathNotFoundException("Destination is the same as Origin");
        }
    }

}
