package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.PlanRateDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, January 2024
 * Time: 3:55 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface PlanRateService {

    /**
     * Save the plan rate
     * @param planRateDto
     * @return
     */
    PlanRateDto savePlanRate(PlanRateDto planRateDto);

    /**
     * Save the plan rates
     * @param planRateDtos
     * @return
     */
    List<PlanRateDto> savePlanRates(List<PlanRateDto> planRateDtos);
}
