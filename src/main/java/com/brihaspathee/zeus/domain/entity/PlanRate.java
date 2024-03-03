package com.brihaspathee.zeus.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 04, May 2023
 * Time: 5:54 AM
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
@Table(name = "PLAN_RATE")
public class PlanRate {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "plan_rate_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID planRateSK;

    /**
     * The plan to which the rate is associated
     */
    @ManyToOne
    @JoinColumn(name = "plan_sk", nullable = false, columnDefinition = "varchar")
    private Plan plan;

    /**
     * The geolocaton to which the rate is associated
     */
    @ManyToOne
    @JoinColumn(name = "geo_location_sk", nullable = false, columnDefinition = "varchar")
    private GeoLocation geoLocation;

    /**
     * The premium rate charged for the member
     */
    @Column(name = "plan_premium_rate", nullable = false)
    private BigDecimal planPremiumRate;

    /**
     * The age of the member
     */
    @Column(name = "age", nullable = false)
    private int age;

    /**
     * The gender of the member
     */
    @Column(name = "gender_type_code", columnDefinition = "varchar", length = 10, nullable = false)
    private String genderTypeCode;

    /**
     * Identifies if the member is a tobacco user
     */
    @Column(name="tobacco_ind")
    private boolean tobaccoInd;

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
}
