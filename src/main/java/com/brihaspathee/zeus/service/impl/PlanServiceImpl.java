package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.domain.repository.GeoLocationRepository;
import com.brihaspathee.zeus.domain.repository.PlanRepository;
import com.brihaspathee.zeus.dto.rate.MemberRateRequestDto;
import com.brihaspathee.zeus.dto.rate.MemberRateResponseDto;
import com.brihaspathee.zeus.dto.rate.RateRequestDto;
import com.brihaspathee.zeus.dto.rate.RateResponseDto;
import com.brihaspathee.zeus.mapper.interfaces.PlanMapper;
import com.brihaspathee.zeus.service.interfaces.GeoLocationService;
import com.brihaspathee.zeus.service.interfaces.PlanService;
import com.brihaspathee.zeus.web.model.GeoLocationDto;
import com.brihaspathee.zeus.web.model.PlanDto;
import com.brihaspathee.zeus.web.model.PlanRateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * Instance of the geolocation service
     */
    private final GeoLocationService geoLocationService;

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
        Plan plan = null;
        Optional<Plan> optionalPlan = planRepository.findPlanByPlanId(planDto.getPlanId());
        plan = optionalPlan.orElseGet(() -> planRepository
                .save(planMapper.planDtoToPlan(planDto)));
        return planMapper.planToPlanDto(plan);
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

    /**
     * Get member rates
     * @param rateRequestDto
     * @return
     */
    @Override
    public RateResponseDto getMemberRates(RateRequestDto rateRequestDto) {
        RateResponseDto rateResponseDto = RateResponseDto.builder()
                .planId(rateRequestDto.getPlanId())
                .stateTypeCode(rateRequestDto.getStateTypeCode())
                .fipsCode(rateRequestDto.getFipsCode())
                .zipCode(rateRequestDto.getZipCode())
                .isException(false)
                .build();
        GeoLocationDto geoLocationDto = geoLocationService.getPlansByStateFipsAndZip(rateRequestDto.getStateTypeCode(),
                rateRequestDto.getFipsCode(),
                rateRequestDto.getZipCode());
        if(geoLocationDto != null){
            List<PlanDto> planDtos = geoLocationDto.getPlans();
            log.info("Matched Location Dto:{}", geoLocationDto);
            if(!planDtos.isEmpty()){
                Optional<PlanDto> optionalPlanDto =
                        planDtos.stream()
                                .filter(planDto ->
                                        planDto.getPlanId()
                                                .equals(rateRequestDto.getPlanId()))
                                .findFirst();
                if(optionalPlanDto.isPresent()){
                    PlanDto planDto = optionalPlanDto.get();
                    log.info("Matched plan:{}", planDto);
                    List<MemberRateResponseDto> memberRateResponseDtos = new ArrayList<>();
                    rateRequestDto.getMemberRateRequestDtos().forEach(memberRateRequestDto -> {
                        BigDecimal memberRate = getMemberRate(planDto, geoLocationDto, memberRateRequestDto);
                        MemberRateResponseDto memberRateResponseDto = MemberRateResponseDto.builder()
                                .memberRateCode(memberRateRequestDto.getMemberRateCode())
                                .memberRate(memberRate)
                                .build();
                        memberRateResponseDtos.add(memberRateResponseDto);
                    });
                    rateResponseDto.setMemberRateResponseDtos(memberRateResponseDtos);
                }else {
                    rateResponseDto.setException(true);
                    // No matching plan present in the location
                    rateResponseDto.setExceptionCode("1500003");
                }
            }else{
                rateResponseDto.setException(true);
                // No plans present in the location
                rateResponseDto.setExceptionCode("1500002");
            }
        }else{
            rateResponseDto.setException(true);
            // No matching geolocation found
            rateResponseDto.setExceptionCode("1500001");
        }
        return rateResponseDto;
    }

    /**
     * Get member rate
     * @param planDto
     * @param geoLocationDto
     * @param memberRateRequestDto
     * @return
     */
    private BigDecimal getMemberRate(PlanDto planDto,
                                     GeoLocationDto geoLocationDto,
                                     MemberRateRequestDto memberRateRequestDto){
        List<PlanRateDto> planRateDtos = planDto.getPlanRateDtos().stream().toList();
        log.info("PlanRate Dto Size:{}", planRateDtos.size());
//        log.info("PlanRate Dtos:{}", planRateDtos);
        Optional<PlanRateDto> optionalPlanRateDto = planRateDtos.stream().filter(planRateDto -> planRateDto.getAge() == memberRateRequestDto.getAge() &&
                planRateDto.isTobaccoInd() == memberRateRequestDto.isTobaccoInd() &&
                planRateDto.getGenderTypeCode().equalsIgnoreCase(memberRateRequestDto.getGenderTypeCode()) &&
                planRateDto.getGeoLocationDto().getGeoLocationSK().equals(geoLocationDto.getGeoLocationSK())).findFirst();
        if(optionalPlanRateDto.isPresent()){
            log.info("Plan Rate:{}", optionalPlanRateDto.get());
            return optionalPlanRateDto.get().getPlanPremiumRate();
        }else{
            return BigDecimal.ZERO;
        }
    }

}
