package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.PlanRate;
import com.brihaspathee.zeus.web.model.PlanRateDto;

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
public interface PlanRateMapper {

    /**
     * Converts planRate dto to planRate entity
     * @param planRateDto
     * @return
     */
    PlanRate planRateDtoToPlanRate(PlanRateDto planRateDto);

    /**
     * Converts planRate entity to planRate dto
     * @param planRate
     * @return
     */
    PlanRateDto platRatesToPlanRateDtos(PlanRate planRate);

    /**
     * Coverts the list of planRate dtos into planRate entities
     * @param planRateDtos
     * @return
     */
    List<PlanRate> planRateDtosToPlanRates(List<PlanRateDto> planRateDtos);

    /**
     * Converts planRate entities into planRate dtos
     * @param planRates
     * @return
     */
    List<PlanRateDto> planRatesToPlanRateDtos(List<PlanRate> planRates);
}
