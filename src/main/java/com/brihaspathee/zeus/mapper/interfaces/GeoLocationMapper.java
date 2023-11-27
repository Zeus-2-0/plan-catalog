package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import com.brihaspathee.zeus.web.model.GeoLocationDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 09, May 2023
 * Time: 10:19 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface GeoLocationMapper {

    /**
     * Converts geolocation dto to geolocation entity
     * @param geoLocationDto
     * @return
     */
    GeoLocation locationDtoToLocation(GeoLocationDto geoLocationDto);

    /**
     * Converts geolocation entity to geolocation dto
     * @param geoLocation
     * @return
     */
    GeoLocationDto locationToLocationDto(GeoLocation geoLocation);

    /**
     * Coverts the list of geolocation dtos into geolocation entities
     * @param geoLocationDtos
     * @return
     */
    List<GeoLocation> locationDtosToLocations(List<GeoLocationDto> geoLocationDtos);

    /**
     * Converts geolocation entities into geolocation dtos
     * @param geoLocations
     * @return
     */
    List<GeoLocationDto> locationsToLocationDtos(List<GeoLocation> geoLocations);
}
