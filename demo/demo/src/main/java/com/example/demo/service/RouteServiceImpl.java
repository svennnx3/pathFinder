package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.model.Route;
import com.example.demo.utils.RouteSearcher;
import com.example.demo.validator.RouteValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final CountryService countryService;

    @Override
    public Route findRoute(String origin, String destination) {
        Map<String,Country> countries = countryService.fetchCountries().stream().collect(Collectors.toMap(Country::getName, Function.identity()));
        RouteValidator validator = new RouteValidator(countries);
        Country originCountry = validator.checkDoesCountryIsInList(origin);
        Country destinationCountry = validator.checkDoesCountryIsInList(destination);
        validator.verifyThatRouteIsPossibleToFind(originCountry,destinationCountry);
        RouteSearcher routeSearcher = new RouteSearcher(countries,originCountry,destinationCountry);
        List<String> routes = routeSearcher.findRoute();
        return new Route(routes);
    }



}
