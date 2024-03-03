package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.dto.rate.RateRequestDto;
import com.brihaspathee.zeus.dto.rate.RateResponseDto;
import com.brihaspathee.zeus.web.model.GeoLocationList;
import com.brihaspathee.zeus.web.model.PlanList;
import com.brihaspathee.zeus.web.model.WelcomeDto;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, December 2022
 * Time: 4:26 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/plan-catalog")
@Validated
public interface PlanAPI {

    /**
     * Get all plans
     * @return
     */
    @GetMapping
    ResponseEntity<ZeusApiResponse<PlanList>> getPlans();

    /**
     * Get plans by state
     * @param stateTypeCode
     * @return
     */
    @GetMapping("/state/{state}")
    ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByState(
            @PathVariable(name = "state") String stateTypeCode);

    /**
     * Get plans by county
     * @param fipsCode
     * @return
     */
    @GetMapping("/county/{fips}")
    ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByCounty(
            @PathVariable(name = "fips") String fipsCode);

    /**
     * Get plans by zip
     * @param zipCode
     * @return
     */
    @GetMapping("/zip/{zip}")
    ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByZip(
            @PathVariable(name = "zip") String zipCode);

    /**
     * Get rates for the member
     * @param rateRequestDto
     * @return
     */
    @PostMapping("/member-rate")
    ResponseEntity<ZeusApiResponse<RateResponseDto>> getMemberRates(
            @RequestBody RateRequestDto rateRequestDto);
}
