package com.brihaspathee.zeus.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanRateDto {

    private UUID planRateSK;

    private PlanDto planDto;

    private GeoLocationDto geoLocationDto;

    private BigDecimal planPremiumRate;

    private int age;

    private String genderTypeCode;

    private boolean tobaccoInd;

    @Override
    public String toString() {
        return "PlanRateDto{" +
                "planRateSK=" + planRateSK +
                ", planPremiumRate=" + planPremiumRate +
                ", age=" + age +
                ", genderTypeCode='" + genderTypeCode + '\'' +
                ", tobaccoInd=" + tobaccoInd +
                '}';
    }
}
