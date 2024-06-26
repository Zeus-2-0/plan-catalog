package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, December 2022
 * Time: 2:44 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, UUID> {

    /**
     * Find plan by plan id
     * @param planId
     * @return
     */
    Optional<Plan> findPlanByPlanId(String planId);
}
