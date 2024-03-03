package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2023
 * Time: 9:48 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation, UUID> {

    /**
     * Find the geo location entity by state, county and zip code
     * @param stateTypeCode
     * @param fipsCode
     * @param zipCode
     * @return
     */
    Optional<GeoLocation> findGeoLocationByStateTypeCodeAndAndFipsCodeAndZipCode(String stateTypeCode,
                                                                                 String fipsCode,
                                                                                 String zipCode);

    /**
     * Find the geo location entity by state
     * @param stateTypeCode
     * @return
     */
    List<GeoLocation> findGeoLocationByStateTypeCode(String stateTypeCode);

    /**
     * Find the geo location entity by zip code
     * @param zipCode
     * @return
     */
    List<GeoLocation> findGeoLocationByZipCode(String zipCode);

    /**
     * Find the geo location entity by county
     * @param fipsCode
     * @return
     */
    List<GeoLocation> findGeoLocationByFipsCode(String fipsCode);
}
