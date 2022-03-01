package com.example.demo.mapper;

import com.example.demo.dto.CountryDto;
import com.example.demo.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CountryMapperTest {
    @Autowired
    private CountryMapper mapper;
    private List<CountryDto> countryDtoList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        countryDtoList = List.of(new CountryDto("POL", "Europe", List.of("LT,UKR")),
                new CountryDto("GER", "Europe", List.of("POL,FR")),
                new CountryDto("UKR", "Europe", List.of("RUS,PL,BLR"))
        );
    }

    @Test
    public void testFromDtoWithInvalidRegion() {
        CountryDto dto = new CountryDto("POL", "husadasuhdas", Collections.emptyList());
        Country country = mapper.fromDto(dto);
        assertThat(country.getRegion()).isNull();
    }

    @Test
    public void testDtoPropertiesAreEqualToObject() {
        CountryDto dto = new CountryDto("POL", "Europe", List.of("RUS,UKR"));
        Country country = mapper.fromDto(dto);
        assertCountryDtoPropertiesAreEqualsToCountryProperties(dto, country);
    }

    @Test
    public void testDtoPropertiesAreEqualToObjectFromList() {
        List<Country> countries = mapper.fromDto(countryDtoList);
        for (int i = 0; i < countries.size(); i++)
            assertCountryDtoPropertiesAreEqualsToCountryProperties(countryDtoList.get(i), countries.get(i));
    }


    private void assertCountryDtoPropertiesAreEqualsToCountryProperties(CountryDto countryDto, Country country) {
        assertThat(countryDto.getCca3()).isEqualTo(country.getName());
        assertThat(countryDto.getBorders()).isEqualTo(country.getBorders());
        assertThat(countryDto.getRegion()).isEqualToIgnoringCase(country.getRegion().name());
        assertThat(countryDto.getRegion()).isNotNull();
    }
}
