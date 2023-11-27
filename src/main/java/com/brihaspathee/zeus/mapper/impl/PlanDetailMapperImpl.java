package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.domain.entity.PlanDetail;
import com.brihaspathee.zeus.mapper.interfaces.PlanDetailMapper;
import com.brihaspathee.zeus.mapper.interfaces.PlanMapper;
import com.brihaspathee.zeus.web.model.PlanDetailDto;
import com.brihaspathee.zeus.web.model.PlanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 09, May 2023
 * Time: 1:19 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlanDetailMapperImpl implements PlanDetailMapper {

    /**
     * Converts planDetail dto to planDetail entity
     * @param planDetailDto plan detail dto to be converted to entity
     * @return plan detail entity that was converted
     */
    @Override
    public PlanDetail planDetailDtoToPlanDetail(PlanDetailDto planDetailDto) {
        if(planDetailDto == null){
            return null;
        }
        return PlanDetail.builder()
                .planDetailSK(planDetailDto.getPlanDetailSK())
                .plan(Plan.builder().planSK(planDetailDto.getPlan().getPlanSK()).build())
                .planMarketingName(planDetailDto.getPlanMarketingName())
                .planLevelTypeCode(planDetailDto.getPlanLevelTypeCode())
                .build();
    }

    /**
     * Converts planDetail entity to planDetail dto
     * @param planDetail plan detail entity that needs to be converted to dto
     * @return plan detail dto that was converted
     */
    @Override
    public PlanDetailDto planDetailToPlanDetailDto(PlanDetail planDetail) {
        if(planDetail == null){
            return null;
        }
        return PlanDetailDto.builder()
                .planDetailSK(planDetail.getPlanDetailSK())
                .plan(PlanDto.builder().planSK(planDetail.getPlan().getPlanSK()).build())
                .planMarketingName(planDetail.getPlanMarketingName())
                .planLevelTypeCode(planDetail.getPlanLevelTypeCode())
                .build();
    }

    /**
     * Coverts the list of planDetail dtos into planDetail entities
     * @param planDetailDtos dtos that needs to be converted to entities
     * @return converted entities
     */
    @Override
    public List<PlanDetail> planDetailDtosToPlanDetails(List<PlanDetailDto> planDetailDtos) {

        return planDetailDtos.stream().map(this::planDetailDtoToPlanDetail).collect(Collectors.toList());
    }

    /**
     * Converts planDetail entities into planDetail dtos
     * @param planDetails entities that needs to be converted to dtos
     * @return converted entities
     */
    @Override
    public List<PlanDetailDto> planDetailsToPlanDetailDtos(List<PlanDetail> planDetails) {

        return planDetails.stream().map(this::planDetailToPlanDetailDto).collect(Collectors.toList());
    }
}
