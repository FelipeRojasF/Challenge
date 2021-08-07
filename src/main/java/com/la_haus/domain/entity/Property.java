package com.la_haus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Properties")
/*@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name="property_type" )*/
//implements Serializable
public class Property {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;

    @NotNull(message = "title cannot be null")
    private String title;

    private String description;

    @NotNull(message = "location cannot be null")
    @Embedded
    private Location location;

    @Embedded
    private Pricing pricing;

    @NotNull(message = "property type cannot be null")
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @NotNull(message = "bedrooms cannot be null")
    private int bedrooms;

    @NotNull(message = "bathrooms cannot be null")
    private int bathrooms;

    private int parkingSpots;

    @NotNull(message = "area cannot be null")
    private int area;

    @ElementCollection
    private List<String> photos;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

}

