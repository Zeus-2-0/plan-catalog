package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.PlanDetail;
import com.brihaspathee.zeus.web.model.PlanDetailDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 09, May 2023
 * Time: 10:20 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface PlanDetailMapper {

    /**
     * Converts planDetail dto to planDetail entity
     * @param planDetailDto
     * @return
     */
    PlanDetail planDetailDtoToPlanDetail(PlanDetailDto planDetailDto);

    /**
     * Converts planDetail entity to planDetail dto
     * @param planDetail
     * @return
     */
    PlanDetailDto planDetailToPlanDetailDto(PlanDetail planDetail);

    /**
     * Coverts the list of planDetail dtos into planDetail entities
     * @param planDetailDtos
     * @return
     */
    List<PlanDetail> planDetailDtosToPlanDetails(List<PlanDetailDto> planDetailDtos);

    /**
     * Converts planDetail entities into planDetail dtos
     * @param planDetails
     * @return
     */
    List<PlanDetailDto> planDetailsToPlanDetailDtos(List<PlanDetail> planDetails);
}
