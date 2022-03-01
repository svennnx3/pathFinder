package com.example.demo.mapper;

import com.example.demo.dto.CountryDto;
import com.example.demo.model.Country;
import com.example.demo.utils.Region;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public Country fromDto(CountryDto countryDto) {
        if (countryDto == null) {
            return null;
        }
        List<String> borders = null;
        String name = countryDto.getCca3();
        Region region = regionNameToRegion(countryDto.getRegion());
        List<String> countryDtoBorders = countryDto.getBorders();
        if ( countryDtoBorders != null ) {
            borders = new ArrayList<>(countryDtoBorders);
        }
        return new Country(name,region,borders);
    }

    @Override
    public List<Country> fromDto(List<CountryDto> countryDtoList) {
        if (countryDtoList == null) {
            return Collections.emptyList();
        }
        List<Country> list = new ArrayList<>();
        for (CountryDto countryDto : countryDtoList) {
            list.add(fromDto(countryDto));
        }
        return list;
    }
}
