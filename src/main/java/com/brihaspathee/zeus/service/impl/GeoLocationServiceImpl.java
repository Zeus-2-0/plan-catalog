package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.domain.entity.PlanRate;
import com.brihaspathee.zeus.domain.repository.GeoLocationRepository;
import com.brihaspathee.zeus.mapper.interfaces.GeoLocationMapper;
import com.brihaspathee.zeus.mapper.interfaces.PlanMapper;
import com.brihaspathee.zeus.service.interfaces.GeoLocationService;
import com.brihaspathee.zeus.web.model.GeoLocationDto;
import com.brihaspathee.zeus.web.model.PlanDto;
import com.brihaspathee.zeus.web.model.PlanRateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
     * Instance of the plan mapper
     */
    private final PlanMapper planMapper;

    /**
     * Create the requested geo location
     * @param geoLocationDto
     * @return
     */
    @Override
    public GeoLocationDto createGeoLocation(GeoLocationDto geoLocationDto) {
        GeoLocation geoLocation = null;
        Optional<GeoLocation> optionalGeoLocation =
                geoLocationRepository
                        .findGeoLocationByStateTypeCodeAndAndFipsCodeAndZipCode(
                                geoLocationDto.getStateTypeCode(),
                                geoLocationDto.getFipsCode(),
                                geoLocationDto.getZipCode()
                        );
        geoLocation = optionalGeoLocation.orElseGet(() ->
                geoLocationRepository
                        .save(geoLocationMapper.locationDtoToLocation(geoLocationDto)));

        return geoLocationMapper.locationToLocationDto(geoLocation);
    }

    /**
     * Create the requested geolocations
     * @param geoLocationDtos
     * @return
     */
    @Override
    public List<GeoLocationDto> createGeoLocations(List<GeoLocationDto> geoLocationDtos) {
        return geoLocationDtos.stream().map(this::createGeoLocation).toList();
    }

    /**
     * Get all plans by zip code
     * @param zipCode
     * @return
     */
    @Override
    public List<GeoLocationDto> getPlansByZipCode(String zipCode) {
        log.info("Inside the service method");
        List<GeoLocation> geoLocations = geoLocationRepository.findGeoLocationByZipCode(zipCode);
        return getGeoLocationDtos(geoLocations);
    }

    /**
     * Get the geolocation dtos populated with the plans that are offered in the geolocation
     * @param geoLocations
     * @return
     */
    private List<GeoLocationDto> getGeoLocationDtos(List<GeoLocation> geoLocations) {
        if(!geoLocations.isEmpty()){
            List<GeoLocationDto> geoLocationDtos = new ArrayList<>();
            geoLocations.forEach(geoLocation -> {
                GeoLocationDto geoLocationDto = getGeoLocationDto(geoLocation);
                geoLocationDtos.add(geoLocationDto);
            });
            return geoLocationDtos;
        }else{
            return null;
        }
    }

    /**
     * Convert geolocation entity into geolocation dto with plans populated
     * @param geoLocation
     * @return
     */
    private GeoLocationDto getGeoLocationDto(GeoLocation geoLocation) {
        List<Plan> plans = geoLocation.getPlans().stream().toList();
        List<PlanDto> planDtos = planMapper.plansToPlanDtos(plans);
        planDtos.forEach(planDto -> {
            Set<PlanRateDto> planRateDtos = planDto.getPlanRateDtos();
            planRateDtos = planRateDtos.stream().filter(planRateDto ->
                    planRateDto.getGeoLocationDto().getGeoLocationSK().equals(geoLocation.getGeoLocationSK()) &&
                    planRateDto.getPlanDto().getPlanSK().equals(planDto.getPlanSK())).collect(Collectors.toSet());
            planDto.setPlanRateDtos(planRateDtos);
        });
        GeoLocationDto geoLocationDto = geoLocationMapper.locationToLocationDto(geoLocation);
        geoLocationDto.setPlans(planDtos);
        return geoLocationDto;
    }


    /**
     * Get all the plans associated with the state, fips and zip
     * @param stateTypeCode
     * @param fipsCode
     * @param zipCode
     * @return
     */
    @Override
    public GeoLocationDto getPlansByStateFipsAndZip(String stateTypeCode, String fipsCode, String zipCode) {
        Optional<GeoLocation> optionalGeoLocation = geoLocationRepository.findGeoLocationByStateTypeCodeAndAndFipsCodeAndZipCode(stateTypeCode,
                fipsCode, zipCode);
        return optionalGeoLocation.map(this::getGeoLocationDto).orElse(null);
    }
}
