package com.la_haus.domain.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Location {
    private float latitude;
    private float longitude;
}
