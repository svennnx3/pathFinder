package com.example.demo.model;

import com.example.demo.utils.Region;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Country {
    private final String name;
    private final Region region;
    private final List<String> borders;
}
