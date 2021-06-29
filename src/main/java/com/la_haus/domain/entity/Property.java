package com.la_haus.domain.entity;

import lombok.*;

@Data
public class Property {
    private int id;
    private String title;
    private String description;
    private Location location;
    private Pricing pricing;
    private String propertyType;
    private int bedrooms;
    private int bathrooms;
    private int parkingSpots;
    private int area;
    private String[] photos;
    private String createdAt;
    private String updatedAt;
    private String status;
}
