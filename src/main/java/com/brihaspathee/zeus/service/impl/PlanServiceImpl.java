package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.domain.repository.PlanRepository;
import com.brihaspathee.zeus.mapper.interfaces.PlanMapper;
import com.brihaspathee.zeus.service.interfaces.PlanService;
import com.brihaspathee.zeus.web.model.PlanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, January 2024
 * Time: 3:14 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    /**
     * Plan repository instance
     */
    private final PlanRepository planRepository;

    /**
     * Plan mapper instance
     */
    private final PlanMapper planMapper;

    /**
     * Save the plan
     * @param planDto
     * @return
     */
    @Override
    public PlanDto savePlan(PlanDto planDto) {
        Plan plan = planMapper.planDtoToPlan(planDto);
        return planMapper.planToPlanDto(planRepository.save(plan));
    }

    /**
     * Save the plans
     * @param planDtos
     * @return
     */
    @Override
    public List<PlanDto> savePlans(List<PlanDto> planDtos) {
        return planDtos.stream().map(this::savePlan).toList();
    }
}
