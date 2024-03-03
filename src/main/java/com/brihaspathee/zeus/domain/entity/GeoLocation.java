package com.brihaspathee.zeus.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 04, May 2023
 * Time: 5:14 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.entity
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GEO_LOCATION")
public class GeoLocation {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "geo_location_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID geoLocationSK;

    /**
     * The state that in which the plan is provided
     */
    @Column(name = "state_type_code", columnDefinition = "varchar", length = 10, nullable = false)
    private String stateTypeCode;

    /**
     * The fips code in which the plan is provided
     */
    @Column(name = "fips_code", columnDefinition = "varchar", length = 20, nullable = false)
    private String fipsCode;

    /**
     * The zip code in which the plan is provided
     */
    @Column(name = "zip_code", columnDefinition = "varchar", length = 20, nullable = false)
    private String zipCode;

    /**
     * The list of all the plans that are offered at the location
     */
    @ManyToMany(mappedBy = "geoLocations")
    private Set<Plan> plans;

    /**
     * The list of all the plan rates associated with the plan
     */
    @OneToMany(mappedBy = "geoLocation", fetch = FetchType.EAGER)
    private Set<PlanRate> planRates;

    /**
     * The date when the record was created
     */
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * The date when the record was updated
     */
    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() {
        return "GeoLocation{" +
                "geoLocationSK=" + geoLocationSK +
                ", stateTypeCode='" + stateTypeCode + '\'' +
                ", fipsCode='" + fipsCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
