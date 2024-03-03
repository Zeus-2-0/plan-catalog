package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.domain.repository.PlanRepository;
import com.brihaspathee.zeus.dto.rate.RateRequestDto;
import com.brihaspathee.zeus.dto.rate.RateResponseDto;
import com.brihaspathee.zeus.service.interfaces.GeoLocationService;
import com.brihaspathee.zeus.service.interfaces.PlanService;
import com.brihaspathee.zeus.web.model.GeoLocationDto;
import com.brihaspathee.zeus.web.model.GeoLocationList;
import com.brihaspathee.zeus.web.model.PlanList;
import com.brihaspathee.zeus.web.resource.interfaces.PlanAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, December 2022
 * Time: 4:27 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PlanAPIImpl implements PlanAPI {

    /**
     * Plan repository instance
     */
    private final PlanRepository planRepository;

    /**
     * The Geolocation service instance
     */
    private final GeoLocationService geoLocationService;

    /**
     * The Plan service instance
     */
    private final PlanService planService;

    /**
     * Get all plans
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<PlanList>> getPlans() {
        List<Plan> plans = planRepository.findAll();
        PlanList planList = PlanList.builder().plans(plans).build();
        ZeusApiResponse<PlanList> apiResponse = ZeusApiResponse.<PlanList>builder()
                .response(planList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get all the plans by state
     * @param stateTypeCode
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByState(String stateTypeCode) {
        return null;
    }

    /**
     * Get all the plans by fips code
     * @param fipsCode
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByCounty(String fipsCode) {
        return null;
    }

    /**
     * Get all the plans by zip code
     * @param zipCode
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByZip(String zipCode) {
        log.info("Zip Code:{}", zipCode);
        List<GeoLocationDto> geoLocationDtos = geoLocationService.getPlansByZipCode(zipCode);
        ZeusApiResponse<GeoLocationList> apiResponse = ZeusApiResponse.<GeoLocationList>builder()
                .response(GeoLocationList.builder()
                        .geoLocationDtos(geoLocationDtos)
                        .build())
                .message("API Call Successful")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get member rates
     * @param rateRequestDto
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<RateResponseDto>> getMemberRates(RateRequestDto rateRequestDto) {
        RateResponseDto rateResponseDto = planService.getMemberRates(rateRequestDto);
        ZeusApiResponse<RateResponseDto> apiResponse = ZeusApiResponse.<RateResponseDto>builder()
                .response(rateResponseDto)
                .message("API Call Successful")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }
}
