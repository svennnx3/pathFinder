package com.example.demo.mapper;

import com.example.demo.dto.CountryDto;
import com.example.demo.model.Country;
import com.example.demo.utils.Region;

import java.util.Arrays;
import java.util.List;

public interface CountryMapper {
    Country fromDto(CountryDto countryDto);

    List<Country> fromDto(List<CountryDto> countryDtoList);

    default Region regionNameToRegion(String region) {
        return Arrays.stream(Region.values()).filter(r -> r.name().equalsIgnoreCase(region)).findFirst().orElse(null);
    }
}
