package com.greeneyed.samples.webservices.restfulwebservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.greeneyed.samples.webservices.restfulwebservice.entity.User;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveUsersWithFilter() {
        User filteredUser = new User("Mellany", LocalDate.of(2002, 8, 19));

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("UserIdFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("id"));
        MappingJacksonValue mapping = new MappingJacksonValue(filteredUser);
        mapping.setFilters(filters);
        return mapping;
    }
}