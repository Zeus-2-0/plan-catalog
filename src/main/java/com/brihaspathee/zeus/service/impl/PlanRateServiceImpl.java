package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.PlanRate;
import com.brihaspathee.zeus.domain.repository.PlanRateRepository;
import com.brihaspathee.zeus.mapper.interfaces.PlanRateMapper;
import com.brihaspathee.zeus.service.interfaces.PlanRateService;
import com.brihaspathee.zeus.web.model.PlanRateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, January 2024
 * Time: 3:56 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlanRateServiceImpl implements PlanRateService {

    /**
     * Repository instance
     */
    private final PlanRateRepository planRateRepository;

    /**
     * Plan Rate mapper instance
     */
    private final PlanRateMapper planRateMapper;

    /**
     * Save the plan rate
     * @param planRateDto
     * @return
     */
    @Override
    public PlanRateDto savePlanRate(PlanRateDto planRateDto) {
        PlanRate planRate = planRateMapper.planRateDtoToPlanRate(planRateDto);
        return planRateMapper.platRatesToPlanRateDtos(planRateRepository.save(planRate));
    }

    /**
     * Save the plan rates
     * @param planRateDtos
     * @return
     */
    @Override
    public List<PlanRateDto> savePlanRates(List<PlanRateDto> planRateDtos) {
        return planRateDtos.stream().map(this::savePlanRate).toList();
    }
}
