package com.brihaspathee.zeus.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, December 2022
 * Time: 2:37 PM
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
@Table(name = "PLAN")
public class Plan {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "plan_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID planSK;

    /**
     * The location in which the plan is provided
     */
    @ManyToOne
    @JoinColumn(name = "geo_location_sk", nullable = false, columnDefinition = "varchar")
    private GeoLocation geoLocation;

    /**
     * The list of all the plan rates associated with the plan
     */
    @OneToMany(mappedBy = "plan", fetch = FetchType.EAGER)
    private Set<PlanRate> planRates;

    /**
     * A unique id that is associated with the plan
     */
    @Column(name = "plan_id", columnDefinition = "varchar", length = 50, nullable = false)
    private String planId;

    /**
     * The name of the plan
     */
    @Column(name = "plan_name", columnDefinition = "varchar", length = 100, nullable = false)
    private String planName;

    /**
     * The product type of the plan
     */
    @Column(name = "product_type_code", columnDefinition = "varchar", length = 50, nullable = false)
    private String productTypeCode;

    /**
     * A short description of the plan
     */
    @Column(name = "plan_description", columnDefinition = "varchar", length = 100, nullable = false)
    private String planDescription;

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
        return "Plan{" +
                "planSK=" + planSK +
                ", geoLocation=" + geoLocation +
                ", planId='" + planId + '\'' +
                ", planName='" + planName + '\'' +
                ", productTypeCode='" + productTypeCode + '\'' +
                ", planDescription='" + planDescription + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
