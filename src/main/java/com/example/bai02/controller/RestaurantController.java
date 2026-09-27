package com.example.bai02.controller;

import com.example.bai02.model.MenuItem;
import com.example.bai02.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{restaurantId}/menu")
    public List<MenuItem> getMenu(@PathVariable("restaurantId") Long restaurantId) {
        return restaurantService.getMenuByRestaurantId(restaurantId);
    }
}
