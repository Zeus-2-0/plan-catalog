package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import com.brihaspathee.zeus.mapper.interfaces.GeoLocationMapper;
import com.brihaspathee.zeus.web.model.GeoLocationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 09, May 2023
 * Time: 1:18 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GeoLocationMapperImpl implements GeoLocationMapper {

    /**
     * Converts geolocation dto to geolocation entity
     * @param geoLocationDto geolocation dto parameter
     * @return returns the geolocation entity
     */
    @Override
    public GeoLocation locationDtoToLocation(GeoLocationDto geoLocationDto) {
        if(geoLocationDto == null){
            return null;
        }
        return GeoLocation.builder()
                .geoLocationSK(geoLocationDto.getGeoLocationSK())
                .fipsCode(geoLocationDto.getFipsCode())
                .zipCode(geoLocationDto.getZipCode())
                .stateTypeCode(geoLocationDto.getStateTypeCode())
                .build();
    }

    /**
     * Converts geolocation entity to geolocation dto
     * @param geoLocation location parameter
     * @return returns the dto of geolocation
     */
    @Override
    public GeoLocationDto locationToLocationDto(GeoLocation geoLocation) {
        if(geoLocation == null){
            return null;
        }
        return GeoLocationDto.builder()
                .geoLocationSK(geoLocation.getGeoLocationSK())
                .fipsCode(geoLocation.getFipsCode())
                .zipCode(geoLocation.getZipCode())
                .stateTypeCode(geoLocation.getStateTypeCode())
                .build();
    }

    /**
     * Coverts the list of geolocation dtos into geolocation entities
     * @param geoLocationDtos geolocation dtos to be converted to entities
     * @return return the geolocation entities
     */
    @Override
    public List<GeoLocation> locationDtosToLocations(List<GeoLocationDto> geoLocationDtos) {
        return geoLocationDtos.stream().map(this::locationDtoToLocation).collect(Collectors.toList());
    }

    /**
     * Converts geolocation entities into geolocation dtos
     * @param geoLocations geolocation entities to be converted to dtos
     * @return return the geolocation dtos
     */
    @Override
    public List<GeoLocationDto> locationsToLocationDtos(List<GeoLocation> geoLocations) {
        return geoLocations.stream().map(this::locationToLocationDto).collect(Collectors.toList());
    }
}
