package com.la_haus.application.controllers;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.la_haus.application.validation.ApartmentValidator;
import com.la_haus.application.validation.HouseValidator;
import com.la_haus.application.validation.PropertyValidator;
import com.la_haus.domain.entity.Property;
import com.la_haus.domain.entity.PropertyStatus;
import com.la_haus.domain.entity.PropertyType;
import com.la_haus.domain.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;


@Controller
@Validated
public class PropertyController {

    @Autowired
    PropertyRepository propertyRepository;
    PropertyValidator propertyValidator;

    @PostMapping("/v1/properties")
    @ResponseBody
    public Property createProperty(@RequestBody @Valid Property property, BindingResult result){
        propertyValidator = (property.getPropertyType() == PropertyType.HOUSE) ? new HouseValidator(property) : new ApartmentValidator(property);
        property = propertyValidator.completeValidation();
        return propertyRepository.save(property);
    }

    @PutMapping("/v1/properties/{id}")
    @ResponseBody
    public Property updateProperty(@RequestBody Property updateProperty, @PathVariable("id") int id) throws Exception {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new Exception("property not found"+id));
        property.setTitle(updateProperty.getTitle());
        property.setDescription(updateProperty.getDescription());
        property.setLocation(updateProperty.getLocation());
        property.setPricing(updateProperty.getPricing());
        property.setPropertyType( updateProperty.getPropertyType());
        property.setBedrooms( updateProperty.getBedrooms());
        property.setBathrooms( updateProperty.getBathrooms());
        property.setParkingSpots(updateProperty.getParkingSpots());
        property.setArea( updateProperty.getArea());
        property.setPhotos( updateProperty.getPhotos());
        propertyValidator = (property.getPropertyType() == PropertyType.HOUSE) ? new HouseValidator(property) : new ApartmentValidator(property);
        property = propertyValidator.completeValidation();
        return propertyRepository.save(property);
    }

    @GetMapping("/v1/properties")
    @ResponseBody
    public LinkedHashMap<String, Object> getProperties(@RequestParam(name = "status", defaultValue = "ALL", required = false) PropertyStatus status,
                                                       @RequestParam(name = "bbox",required = false) List<Double> bbox,
                                                       @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                       @RequestParam(name = "pageSize", defaultValue = "10", required = false) @Min(value = 10) @Max(value = 20) int pageSize) {


        Pageable pagination = PageRequest.of(page, pageSize);
        //Sort.by("updatedAt").descending().and(Sort.by("createdAt").descending())
        Page<Property> data = null;
        if((status != PropertyStatus.ALL) && (bbox != null)){
            data = propertyRepository.findByLocationAndStatus(bbox.get(0), bbox.get(1), bbox.get(2), bbox.get(3), status.name(), pagination);
        }else{
            if(bbox == null){
                data = propertyRepository.findAllByStatus(status, pagination);
            }else{
                data = propertyRepository.findByLocation(bbox.get(0), bbox.get(1), bbox.get(2), bbox.get(3), pagination);
            }

        }


        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("page", pagination.getPageNumber());
        response.put("pageSize", pagination.getPageSize());
        response.put("total", data.getTotalElements());
        response.put("totalPages", data.getTotalPages());
        response.put("data", data.getContent());

        return  response;
    }

}
