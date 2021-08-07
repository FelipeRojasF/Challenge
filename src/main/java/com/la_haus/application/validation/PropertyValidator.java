package com.la_haus.application.validation;

import com.la_haus.domain.entity.Property;
import com.la_haus.domain.entity.PropertyStatus;
import com.la_haus.domain.entity.PropertyType;

import java.awt.geom.Rectangle2D;

public interface PropertyValidator {

    public  Boolean bedroomsValidation();
    public  Boolean bathroomsValidation();
    public  Boolean areaValidation();
    public  Boolean parkingSpotsValidation();
    public  Property completeValidation();
}
