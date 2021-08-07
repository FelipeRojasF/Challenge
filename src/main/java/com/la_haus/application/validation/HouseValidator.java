package com.la_haus.application.validation;

import com.la_haus.domain.entity.Property;
import com.la_haus.domain.entity.PropertyStatus;

import java.awt.geom.Rectangle2D;

public class HouseValidator implements PropertyValidator{

    private static final int minBedrooms = 1;
    private static final int maxBedrooms = 14;
    private static final int minBathrooms = 1;
    private static final int maxBathrooms = 12;
    private static final int minArea = 50;
    private static final int maxArea = 3000;
    private static final int minParkingSpots = 0;
    private static final double MIN_LATITUDE = 19.296134;
    private static final double MIN_LONGITUDE = -99.296741;
    private static final double MAX_LATITUDE = 19.661237;
    private static final double MAX_LONGITUDE = -98.916339;

    private int bedrooms;
    private int bathrooms;
    private int area;
    private int parkingSpots;
    private Boolean status;
    private double latitude;
    private double longitude;
    private Property property;

    public HouseValidator(Property property){
        bedrooms = property.getBedrooms();
        bathrooms = property.getBathrooms();
        area = property.getArea();
        parkingSpots = property.getParkingSpots();
        latitude = property.getLocation().getLatitude();
        longitude = property.getLocation().getLongitude();
        this.property = property;

        status = this.bedroomsValidation() && this.bathroomsValidation() && this.areaValidation() && this.parkingSpotsValidation();
    }

    @Override
    public Boolean bedroomsValidation() {
        return (bedrooms>=minBedrooms && bedrooms<=maxBedrooms);
    }

    @Override
    public Boolean bathroomsValidation() {
        return (bathrooms>=minBathrooms && bathrooms<=maxBathrooms);
    }

    @Override
    public Boolean areaValidation() {
        return (area>=minArea && area<=maxArea);
    }

    @Override
    public Boolean parkingSpotsValidation() {
        return (parkingSpots>=minParkingSpots);
    }

    @Override
    public Property completeValidation() {
        if (status == Boolean.FALSE) {
            property.setStatus(PropertyStatus.INVALID);
        }else{
            property.setStatus(this.validateLocation());
        }

        return property;
    }

    public PropertyStatus validateLocation(){
        Rectangle2D bbox = new Rectangle2D.Double(MIN_LATITUDE,MIN_LONGITUDE,(MAX_LATITUDE-MIN_LATITUDE),(MAX_LONGITUDE-MIN_LONGITUDE));

        if (bbox.contains(property.getLocation().getLatitude(),property.getLocation().getLongitude()))
            return PropertyStatus.ACTIVE;
        else
            return PropertyStatus.INACTIVE;
    }

}
