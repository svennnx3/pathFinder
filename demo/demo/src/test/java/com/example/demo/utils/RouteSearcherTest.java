package com.example.demo.utils;

import com.example.demo.exception.PathNotFoundException;
import com.example.demo.model.Country;
import com.example.demo.utils.Region;
import com.example.demo.utils.RouteSearcher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class RouteSearcherTest {
    @Test
    public void testPathInVariousContinents() {
        Map<String, Country> countries = buildCountries();
        Country europe1 = countries.get("europe1");
        Country asia6 = countries.get("asia6");
        assertThat(new RouteSearcher(countries, europe1, asia6).findRoute()).isEqualTo(List.of("europe1", "europe3", "asia2", "asia4", "asia5", "asia6"));
    }

    @Test
    public void testPathInVariousContinentsWithReversedOriginAndDestination() {
        Map<String, Country> countries = buildCountries();
        Country europe1 = countries.get("europe1");
        Country asia6 = countries.get("asia6");
        assertThat(new RouteSearcher(countries, asia6, europe1).findRoute()).isEqualTo(List.of("asia6", "asia5", "asia4", "asia2", "europe3", "europe1"));
    }

    @Test
    public void testPathInTheSameContinent() {
        Map<String, Country> countries = buildCountries();
        Country europe1 = countries.get("europe1");
        Country europe3 = countries.get("europe3");
        assertThat(new RouteSearcher(countries, europe1, europe3).findRoute()).isEqualTo(List.of("europe1", "europe3"));
    }

    @Test
    public void testPathInTheSameContinentReversed() {
        Map<String, Country> countries = buildCountries();
        Country europe1 = countries.get("europe1");
        Country europe3 = countries.get("europe3");
        assertThat(new RouteSearcher(countries, europe3, europe1).findRoute()).isEqualTo(List.of("europe3", "europe1"));
    }

    @Test
    public void testCountryWithoutBorders() {
        Map<String, Country> countries = buildCountries();
        Country europe4 = countries.get("europe4");
        Country asia1 = countries.get("asia1");
        assertThatExceptionOfType(PathNotFoundException.class).isThrownBy(() -> new RouteSearcher(countries, europe4, asia1).findRoute());
    }

    public Map<String, Country> buildCountries() {
        Map<String, Country> countries = new HashMap<>();
        countries.put("europe1", new Country("europe1", Region.EUROPE, List.of("europe2", "europe3")));
        countries.put("europe2", new Country("europe2", Region.EUROPE, List.of("europe1", "europe3", "asia1")));
        countries.put("europe3", new Country("europe3", Region.EUROPE, List.of("europe1", "europe2", "asia2")));
        countries.put("europe4", new Country("europe4", Region.EUROPE, List.of()));
        countries.put("asia1", new Country("asia1", Region.ASIA, List.of("europe2", "asia3")));
        countries.put("asia2", new Country("asia2", Region.ASIA, List.of("europe3", "asia4")));
        countries.put("asia3", new Country("asia3", Region.ASIA, List.of("asia1", "asia4")));
        countries.put("asia4", new Country("asia4", Region.ASIA, List.of("asia2", "asia3", "asia5")));
        countries.put("asia5", new Country("asia5", Region.ASIA, List.of("asia4", "asia6")));
        countries.put("asia6", new Country("asia6", Region.ASIA, List.of("asia5")));
        countries.put("asia7", new Country("asia7", Region.ASIA, List.of("asia8", "asia9")));
        countries.put("asia8", new Country("asia8", Region.ASIA, List.of("asia7", "asia9")));
        countries.put("asia9", new Country("asia9", Region.ASIA, List.of("asia7", "asia8")));
        countries.put("asia10", new Country("asia10", Region.ASIA, List.of()));
        return countries;
    }

}
