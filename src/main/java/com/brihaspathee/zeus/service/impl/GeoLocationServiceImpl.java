package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import com.brihaspathee.zeus.domain.repository.GeoLocationRepository;
import com.brihaspathee.zeus.mapper.interfaces.GeoLocationMapper;
import com.brihaspathee.zeus.service.interfaces.GeoLocationService;
import com.brihaspathee.zeus.web.model.GeoLocationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2023
 * Time: 9:55 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GeoLocationServiceImpl implements GeoLocationService {

    /**
     * Instance of the geolocation repository
     */
    private final GeoLocationRepository geoLocationRepository;

    /**
     * Instance of the geolocation mapper
     */
    private final GeoLocationMapper geoLocationMapper;

    /**
     * Create the requested geo location
     * @param geoLocationDto
     * @return
     */
    @Override
    public GeoLocationDto createGeoLocation(GeoLocationDto geoLocationDto) {
        GeoLocation geoLocation = geoLocationMapper.locationDtoToLocation(geoLocationDto);
        return geoLocationMapper.locationToLocationDto(geoLocationRepository.save(geoLocation));
    }

    /**
     * Create the requested geo locations
     * @param geoLocationDtos
     * @return
     */
    @Override
    public List<GeoLocationDto> createGeoLocations(List<GeoLocationDto> geoLocationDtos) {
        return geoLocationDtos.stream().map(this::createGeoLocation).toList();
    }
}
