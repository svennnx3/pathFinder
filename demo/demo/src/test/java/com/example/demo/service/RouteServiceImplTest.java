package com.example.demo.service;

import com.example.demo.exception.PathNotFoundException;
import com.example.demo.model.Country;
import com.example.demo.utils.Region;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class RouteServiceImplTest {
    @Autowired
    private RouteService routeService;

    @MockBean
    private CountryService countryService;

    public List<Country> buildCountryList() {
        return List.of(
                new Country("europe1", Region.EUROPE, List.of("europe2")),
                new Country("europe2", Region.EUROPE, List.of("europe1", "europe3")),
                new Country("europe3", Region.EUROPE, List.of("europe2", "europe4")),
                new Country("europe4", Region.EUROPE, List.of("europe3"))
        );
    }


    @Test
    public void testTheSameOriginAndDestinationWillThrowException() {
        List<Country> countries = buildCountryList();
        Mockito.when(countryService.fetchCountries()).thenReturn(countries);
        assertThatExceptionOfType(PathNotFoundException.class)
                .isThrownBy(() -> routeService.findRoute("europe1", "europe1").getRoutes());
    }

    @Test
    public void testSimpleRoute() {
        List<Country> countries = buildCountryList();
        Mockito.when(countryService.fetchCountries()).thenReturn(countries);
        assertThat(List.of("europe1", "europe2", "europe3", "europe4")).isEqualTo(routeService.findRoute("europe1", "europe4").getRoutes());
    }

}
