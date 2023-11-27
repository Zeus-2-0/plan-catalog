package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.web.model.PlanDto;

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
public interface PlanMapper {

    /**
     * Converts plan dto to plan entity
     * @param planDto
     * @return
     */
    Plan planDtoToPlan(PlanDto planDto);

    /**
     * Converts plan entity to plan dto
     * @param plan
     * @return
     */
    PlanDto planToPlanDto(Plan plan);

    /**
     * Coverts the list of plan dtos into plan entities
     * @param planDtos
     * @return
     */
    List<Plan> planDtosToPlans(List<PlanDto> planDtos);

    /**
     * Converts plan entities into plan dtos
     * @param plans
     * @return
     */
    List<PlanDto> plansToPlanDtos(List<Plan> plans);
}
