package com.la_haus.domain.repository;

import com.la_haus.domain.entity.Property;
import com.la_haus.domain.entity.PropertyStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Page<Property> findAllByStatus(PropertyStatus status, Pageable pageable);
    //Page<Property> findByLatitudeBetween(Double minLongitude,Double maxLongitude, Pageable pageable);

    @Query(value = "SELECT * FROM properties " +
            "WHERE longitude BETWEEN ?1 AND ?3 " +
            "AND latitude BETWEEN ?2 AND ?4 " +
            "AND status = ?5 " +
            "ORDER BY updated_at desc \n-- #pageable\n",
            countQuery = "SELECT count(*) FROM properties",
            nativeQuery = true)
    Page<Property> findByLocationAndStatus(Double minLongitude,
                                  Double minLatitude,
                                  Double maxLongitude,
                                  Double maxLatitude,
                                  String status,
                                  Pageable pageable);

    @Query(value = "SELECT * FROM properties " +
            "WHERE longitude BETWEEN ?1 AND ?3 " +
            "AND latitude BETWEEN ?2 AND ?4 " +
            "ORDER BY updated_at desc \n-- #pageable\n",
            countQuery = "SELECT count(*) FROM properties",
            nativeQuery = true)
    Page<Property> findByLocation(Double minLongitude,
                                  Double minLatitude,
                                  Double maxLongitude,
                                  Double maxLatitude,
                                  Pageable pageable);
}
