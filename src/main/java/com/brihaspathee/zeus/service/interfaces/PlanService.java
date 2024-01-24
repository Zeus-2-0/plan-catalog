package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.PlanDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, January 2024
 * Time: 3:10 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface PlanService {

    /**
     * Save the plan
     * @param planDto
     * @return
     */
    PlanDto savePlan(PlanDto planDto);

    /**
     * Save the plans
     * @param planDtos
     * @return
     */
    List<PlanDto> savePlans(List<PlanDto> planDtos);
}
