package com.brihaspathee.zeus.service.impl;

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
     * Create the requested geo location
     * @param geoLocationDto
     * @return
     */
    @Override
    public GeoLocationDto createGeoLocation(GeoLocationDto geoLocationDto) {
        return null;
    }

    /**
     * Create the requested geo locations
     * @param geoLocationDtos
     * @return
     */
    @Override
    public List<GeoLocationDto> createGeoLocations(List<GeoLocationDto> geoLocationDtos) {
        return null;
    }
}
