package com.brihaspathee.zeus.web.model;

import com.brihaspathee.zeus.domain.entity.PlanDetail;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2023
 * Time: 6:49 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetailDto {

    /**
     * Primary key of the table
     */
    private UUID planDetailSK;

    private PlanDto plan;

    private String planMarketingName;

    private String planLevelTypeCode;
}
