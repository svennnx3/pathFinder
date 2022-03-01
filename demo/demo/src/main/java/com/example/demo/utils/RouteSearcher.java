package com.example.demo.utils;

import com.example.demo.exception.PathNotFoundException;
import com.example.demo.model.Country;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class RouteSearcher {
    private final Map<String, Country> countries;
    private final Map<Country, Boolean> visitedCountry = new HashMap<>();
    private final Map<Country, Country> previousCountry = new HashMap<>();
    private final Country origin;
    private final Country destination;

    public final List<String> findRoute() {
        Country currentCountry = origin;
        Queue<Country> queue = new ArrayDeque<>();
        queue.add(currentCountry);
        visitedCountry.put(currentCountry, true);
        while (!queue.isEmpty()) {
            currentCountry = queue.remove();
            if (!currentCountry.equals(destination)) {
                for (String neighbour : currentCountry.getBorders()) {
                    Country neighbourCountry = countries.get(neighbour);
                    if (!visitedCountry.containsKey(neighbourCountry)) {
                        queue.add(neighbourCountry);
                        visitedCountry.put(neighbourCountry, true);
                        previousCountry.put(neighbourCountry, currentCountry);
                        if (neighbourCountry.equals(destination)) {
                            currentCountry = neighbourCountry;
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }

        if (!currentCountry.equals(destination)) {
            throw new PathNotFoundException("Cannot reach the path");
        }

        List<Country> path = Stream.iterate(destination, Objects::nonNull, previousCountry::get).collect(Collectors.toList());

        return path.stream()
                .map(Country::getName)
                .collect(Collectors.collectingAndThen(Collectors.toList(), RouteSearcher::reverse));
    }

    private static List<String> reverse(List<String> route) {
        Collections.reverse(route);
        return route;
    }
}
