package com.example.demo.service;

import com.example.demo.client.CountryFeignClient;
import com.example.demo.dto.CountryDto;
import com.example.demo.mapper.CountryMapper;
import com.example.demo.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryFeignClient countryFeignClient;
    private final CountryMapper countryMapper;
    @Override
    public List<Country> fetchCountries() {
        List<CountryDto> countryDtos = countryFeignClient.fetchCountries();
        return countryMapper.fromDto(countryDtos);
    }

}
