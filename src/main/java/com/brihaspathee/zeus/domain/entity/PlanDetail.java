package com.brihaspathee.zeus.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2023
 * Time: 9:45 AM
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
@Table(name = "PLAN_DETAIL")
public class PlanDetail {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "plan_detail_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID planDetailSK;

    /**
     * The plan that is associated with the detail
     */
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "plan_sk")
    private Plan plan;

    /**
     * The plan marketing name of the plan
     */
    @Column(name = "plan_marketing_name", columnDefinition = "varchar", length = 20, nullable = false)
    private String planMarketingName;

    /**
     * The plan level type code of the plan
     */
    @Column(name = "plan_level_type_code", columnDefinition = "varchar", length = 20, nullable = false)
    private String planLevelTypeCode;

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
