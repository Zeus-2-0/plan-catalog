package com.brihaspathee.zeus.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
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
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoLocationDto {

    /**
     * Primary key for the geo location in the database
     */
    private UUID geoLocationSK;

    /**
     * The state in which the plans are offered
     */
    private String stateTypeCode;

    /**
     * The fips code in which the plans are offered
     */
    private String fipsCode;

    /**
     * The zip code in which the plans are offered
     */
    private String zipCode;

    /**
     * The plans available in the geolocation
     */
    private List<PlanDto> plans;

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() {
        return "GeoLocationDto{" +
                "geoLocationSK=" + geoLocationSK +
                ", stateTypeCode='" + stateTypeCode + '\'' +
                ", fipsCode='" + fipsCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
