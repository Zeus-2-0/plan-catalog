package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 04, May 2023
 * Time: 4:32 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class RawPlanDto {

    private String stateTypeCode;

    private String fipsCode;

    private String zipCode;

    private String planId;

    private String planName;

    private String planDescription;

    private String productTypeCode;

    private int age;

    private String genderTypeCode;

    private boolean tobaccoInd;

    private BigDecimal premiumRate;

    @Override
    public String toString() {
        return "RawPlanDto{" +
                "stateTypeCode='" + stateTypeCode + '\'' +
                ", fipsCode='" + fipsCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", planId='" + planId + '\'' +
                ", planName='" + planName + '\'' +
                ", planDescription='" + planDescription + '\'' +
                ", genderTypeCode='" + genderTypeCode + '\'' +
                ", tobaccoInd=" + tobaccoInd +
                ", premiumRate=" + premiumRate +
                '}';
    }
}
