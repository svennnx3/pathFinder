package com.example.demo.client;

import com.example.demo.dto.CountryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value="countries", url="https://raw.githubusercontent.com/mledoze/countries")
public interface CountryFeignClient {
    @GetMapping(path = "/master/countries.json")
    List<CountryDto> fetchCountries();
}
