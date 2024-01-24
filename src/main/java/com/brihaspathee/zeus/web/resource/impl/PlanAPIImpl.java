package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.domain.repository.PlanRepository;
import com.brihaspathee.zeus.web.model.GeoLocationList;
import com.brihaspathee.zeus.web.model.PlanList;
import com.brihaspathee.zeus.web.resource.interfaces.PlanAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final PlanRepository planRepository;

    @Override
    public ResponseEntity<ZeusApiResponse<PlanList>> getPlans() {
        List<Plan> plans = planRepository.findAll();
        PlanList planList = PlanList.builder().plans(plans).build();
        ZeusApiResponse<PlanList> apiResponse = ZeusApiResponse.<PlanList>builder()
                .response(planList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByState(String stateTypeCode) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByCounty(String fipsCode) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<GeoLocationList>> getPlansByZip(String zipCode) {
        log.info("Zip Code:{}", zipCode);
        return null;
    }
}
