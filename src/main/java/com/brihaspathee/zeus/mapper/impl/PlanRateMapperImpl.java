package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.domain.entity.PlanRate;
import com.brihaspathee.zeus.mapper.interfaces.PlanRateMapper;
import com.brihaspathee.zeus.web.model.GeoLocationDto;
import com.brihaspathee.zeus.web.model.PlanDto;
import com.brihaspathee.zeus.web.model.PlanRateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
public class PlanRateMapperImpl implements PlanRateMapper {

    /**
     * Converts planRate dto to planRate entity
     * @param planRateDto the dto that needs to converted to entity
     * @return the converted entity
     */
    @Override
    public PlanRate planRateDtoToPlanRate(PlanRateDto planRateDto) {
        if(planRateDto == null){
            return null;
        }
        return PlanRate.builder()
                .planRateSK(planRateDto.getPlanRateSK())
                .plan(Plan.builder().planSK(planRateDto.getPlanDto().getPlanSK()).build())
                .geoLocation(GeoLocation.builder().geoLocationSK(planRateDto.getGeoLocationDto().getGeoLocationSK()).build())
                .planPremiumRate(planRateDto.getPlanPremiumRate())
                .age(planRateDto.getAge())
                .genderTypeCode(planRateDto.getGenderTypeCode())
                .tobaccoInd(planRateDto.isTobaccoInd())
                .build();
    }

    /**
     * Converts planRate entity to planRate dto
     * @param planRate the entity that needs to be converted to dto
     * @return the converted plan rate dto
     */
    @Override
    public PlanRateDto platRatesToPlanRateDtos(PlanRate planRate) {
        if(planRate == null){
            return null;
        }
        return PlanRateDto.builder()
                .planRateSK(planRate.getPlanRateSK())
                .planDto(PlanDto.builder().planSK(planRate.getPlan().getPlanSK()).build())
                .geoLocationDto(GeoLocationDto.builder().geoLocationSK(planRate.getGeoLocation().getGeoLocationSK()).build())
                .planPremiumRate(planRate.getPlanPremiumRate())
                .age(planRate.getAge())
                .genderTypeCode(planRate.getGenderTypeCode())
                .tobaccoInd(planRate.isTobaccoInd())
                .build();
    }

    /**
     * Coverts the list of planRate dtos into planRate entities
     * @param planRateDtos the dtos that needs to be converted to entities
     * @return the converted entities
     */
    @Override
    public List<PlanRate> planRateDtosToPlanRates(List<PlanRateDto> planRateDtos) {

        return planRateDtos.stream().map(this::planRateDtoToPlanRate).collect(Collectors.toList());
    }

    /**
     * Converts planRate entities into planRate dtos
     * @param planRates entities that need to be converted to dtos
     * @return the converted dtos
     */
    @Override
    public List<PlanRateDto> planRatesToPlanRateDtos(List<PlanRate> planRates) {

        return planRates.stream().map(this::platRatesToPlanRateDtos).collect(Collectors.toList());
    }
}
