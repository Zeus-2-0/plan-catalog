package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2023
 * Time: 6:38 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PlanDto {

    private UUID planSK;

    private Set<PlanRateDto> planRateDtos;

    private Set<GeoLocationDto> geoLocationDtos;

    private String planId;

    private String planName;

    private String planDescription;

    private String productTypeCode;

    private PlanDetailDto planDetailDto;

    @Override
    public String toString() {
        return "PlanDto{" +
                "planSK=" + planSK +
                ", planId='" + planId + '\'' +
                ", planName='" + planName + '\'' +
                ", planDescription='" + planDescription + '\'' +
                ", productTypeCode='" + productTypeCode + '\'' +
                ", planDetailDto=" + planDetailDto +
                '}';
    }
}
