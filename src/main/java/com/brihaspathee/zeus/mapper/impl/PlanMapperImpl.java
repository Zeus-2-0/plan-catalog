package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.mapper.interfaces.GeoLocationMapper;
import com.brihaspathee.zeus.mapper.interfaces.PlanDetailMapper;
import com.brihaspathee.zeus.mapper.interfaces.PlanMapper;
import com.brihaspathee.zeus.mapper.interfaces.PlanRateMapper;
import com.brihaspathee.zeus.web.model.GeoLocationDto;
import com.brihaspathee.zeus.web.model.PlanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 09, May 2023
 * Time: 1:20 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlanMapperImpl implements PlanMapper {

    /**
     * Geolocation mapper instance to map the geolocations that are associated with the plan.
     */
    private final GeoLocationMapper geoLocationMapper;

    /**
     * Plan Rate mapper instance to map the plan rates that are associated with the plan.
     */
    private final PlanRateMapper planRateMapper;

    /**
     * Plan Detail mapper instance to map the plan detail that is associated with the plan.
     */
    private final PlanDetailMapper planDetailMapper;

    /**
     * Converts plan dto to plan entity
     * @param planDto plan dto that should be converted to entity
     * @return return the plan entity
     */
    @Override
    public Plan planDtoToPlan(PlanDto planDto) {
        if(planDto == null){
            return null;
        }
        Plan plan = Plan.builder()
                .planSK(planDto.getPlanSK())
                .planId(planDto.getPlanId())
                .planName(planDto.getPlanName())
                .planDescription(planDto.getPlanDescription())
                .productTypeCode(planDto.getProductTypeCode())
                .planDetail(planDetailMapper.planDetailDtoToPlanDetail(planDto.getPlanDetailDto()))
                .build();
        if(planDto.getGeoLocationDtos() != null && !planDto.getGeoLocationDtos().isEmpty()){
            plan.setGeoLocations(
                    new HashSet<>(geoLocationMapper.locationDtosToLocations(
                            new ArrayList<>(planDto.getGeoLocationDtos()))));
        }
        if(planDto.getPlanRateDtos() != null && !planDto.getPlanRateDtos().isEmpty()){
            plan.setPlanRates(
                    new HashSet<>(planRateMapper.planRateDtosToPlanRates(
                            new ArrayList<>(planDto.getPlanRateDtos())
                    ))
            );
        }
        return plan;
    }

    /**
     * Converts plan entity to plan dto
     * @param plan plan entity that needs to be converted to dtos
     * @return return the plan entity
     */
    @Override
    public PlanDto planToPlanDto(Plan plan) {
        if(plan == null){
            return null;
        }
        PlanDto planDto = PlanDto.builder()
                .planSK(plan.getPlanSK())
                .planId(plan.getPlanId())
                .planName(plan.getPlanName())
                .planDescription(plan.getPlanDescription())
                .productTypeCode(plan.getProductTypeCode())
                .planDetailDto(planDetailMapper.planDetailToPlanDetailDto(plan.getPlanDetail()))
                .build();
        if(plan.getGeoLocations() != null && !plan.getGeoLocations().isEmpty()){
            planDto.setGeoLocationDtos(
                    new HashSet<>(geoLocationMapper.locationsToLocationDtos(
                            new ArrayList<>(plan.getGeoLocations()))));
        }
        if(plan.getPlanRates() != null && !plan.getPlanRates().isEmpty()){
            planDto.setPlanRateDtos(
                    new HashSet<>(planRateMapper.planRatesToPlanRateDtos(
                            new ArrayList<>(plan.getPlanRates())
                    ))
            );
        }
        return planDto;
    }

    /**
     * Coverts the list of plan dtos into plan entities
     * @param planDtos plan dtos that need to be converted to entities
     * @return the converted entities
     */
    @Override
    public List<Plan> planDtosToPlans(List<PlanDto> planDtos) {

        return planDtos.stream().map(this::planDtoToPlan).collect(Collectors.toList());
    }

    /**
     * Converts plan entities into plan dtos
     * @param plans plans that need to be converted to dtos
     * @return the converted dtos
     */
    @Override
    public List<PlanDto> plansToPlanDtos(List<Plan> plans) {

        return plans.stream().map(this::planToPlanDto).collect(Collectors.toList());
    }
}
