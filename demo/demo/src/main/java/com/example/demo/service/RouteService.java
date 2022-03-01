package com.example.demo.service;

import com.example.demo.model.Route;

public interface RouteService {
    Route findRoute(String origin, String destination);
}
