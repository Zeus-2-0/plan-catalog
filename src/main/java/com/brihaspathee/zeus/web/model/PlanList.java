package com.brihaspathee.zeus.web.model;

import com.brihaspathee.zeus.domain.entity.Plan;
import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, December 2022
 * Time: 4:28 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanList {

    private List<Plan> plans;
}
