package com.example.demo.controller;

import com.example.demo.model.Route;
import com.example.demo.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    @GetMapping("/routing/{origin}/{destination}")
    public Route findRoute(@PathVariable String origin, @PathVariable String destination){
        return routeService.findRoute(origin,destination);
    }
}
