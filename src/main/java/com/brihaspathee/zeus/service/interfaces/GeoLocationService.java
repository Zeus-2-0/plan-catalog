package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.GeoLocationDto;
import com.brihaspathee.zeus.web.model.PlanDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2023
 * Time: 9:54 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface GeoLocationService {

    /**
     * Create the requested geo location
     * @param geoLocationDto
     * @return
     */
    GeoLocationDto createGeoLocation(GeoLocationDto geoLocationDto);

    /**
     * Create the requested geo locations
     * @param geoLocationDtos
     * @return
     */
    List<GeoLocationDto> createGeoLocations(List<GeoLocationDto> geoLocationDtos);

    /**
     * Get all the plans for the zip code
     * @param zipCode
     * @return
     */
    List<GeoLocationDto> getPlansByZipCode(String zipCode);

    /**
     * Get all the plans associated with state, fips and zip
     * @param stateTypeCode
     * @param fipsCode
     * @param zipCode
     * @return
     */
    GeoLocationDto getPlansByStateFipsAndZip(String stateTypeCode,
                                                   String fipsCode,
                                                   String zipCode);
}
