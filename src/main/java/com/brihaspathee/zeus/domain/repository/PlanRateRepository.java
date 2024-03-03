package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.GeoLocation;
import com.brihaspathee.zeus.domain.entity.Plan;
import com.brihaspathee.zeus.domain.entity.PlanRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2023
 * Time: 9:52 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface PlanRateRepository extends JpaRepository<PlanRate, UUID> {

    /**
     * Find plan rate by age, gender and tobacco indicator
     * @param plan
     * @param geoLocation
     * @param age
     * @param genderTypeCode
     * @param tobacco_ind
     * @return
     */
    Optional<PlanRate> findPlanRatesByPlanAndGeoLocationAndAgeAndGenderTypeCodeAndTobaccoInd(Plan plan,
                                                                               GeoLocation geoLocation,
                                                                               int age,
                                                                               String genderTypeCode,
                                                                               boolean tobacco_ind);
}
