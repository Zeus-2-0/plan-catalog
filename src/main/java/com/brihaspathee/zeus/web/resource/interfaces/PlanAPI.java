package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.web.model.GeoLocationList;
import com.brihaspathee.zeus.web.model.PlanList;
import com.brihaspathee.zeus.web.model.WelcomeDto;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    ResponseEntity<ZeusApiResponse<PlanList>> getPlans();

    @GetMapping("/{state}")
    ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByState(
            @PathVariable(name = "state") String stateTypeCode);

    @GetMapping("/{fips}")
    ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByCounty(
            @PathVariable(name = "fips") String fipsCode);

    @GetMapping("/{zip}")
    ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByZip(
            @PathVariable(name = "zip") String zipCode);
}
