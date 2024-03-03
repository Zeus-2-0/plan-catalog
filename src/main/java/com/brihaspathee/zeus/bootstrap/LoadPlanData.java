package com.brihaspathee.zeus.bootstrap;

import com.brihaspathee.zeus.service.interfaces.GeoLocationService;
import com.brihaspathee.zeus.service.interfaces.PlanRateService;
import com.brihaspathee.zeus.service.interfaces.PlanService;
import com.brihaspathee.zeus.web.model.GeoLocationDto;
import com.brihaspathee.zeus.web.model.PlanDto;
import com.brihaspathee.zeus.web.model.PlanRateDto;
import com.brihaspathee.zeus.web.model.RawPlanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 04, May 2023
 * Time: 6:35 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.bootstrap
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoadPlanData implements CommandLineRunner {

    /**
     * Spring Application Context
     */
    private final ApplicationContext applicationContext;

    /**
     * Geolocation service instance
     */
    private final GeoLocationService geoLocationService;

    /**
     * Plan service instance
     */
    private final PlanService planService;

    /**
     * Plan rate service instance
     */
    private final PlanRateService planRateService;

    private String csvFileLocation = "/Users/plan/files/*.csv";

    /**
     * The method reads a CSV file and loads the plan data
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("Inside the run method");
        Resource[] resources = applicationContext.getResources("file:"+csvFileLocation);

        for(Resource resource: resources){
            InputStream inputStream = resource.getInputStream();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String [] HEADERS = {"state", "fips", "zip_code", "plan_id", "plan_name",
            "plan_description", "product_type_code", "age", "gender",
                    "tobacco_ind", "rate"};
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(HEADERS)
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser csvParser = new CSVParser(fileReader, csvFormat);
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            log.info("CSV Records:{}", csvRecords);
            List<RawPlanDto> rawPlanDtos = extractPlanDetails(csvRecords);
            List<GeoLocationDto> geoLocationDtos = getDistinctGeoLoctions(rawPlanDtos);
            log.info("Distinct geo locations size:{}", geoLocationDtos.size());
            log.info("Distinct geo locations:{}", geoLocationDtos);
            List<PlanDto> planDtos = getDistinctPlans(rawPlanDtos, geoLocationDtos);
            log.info("Distinct plans size:{}", planDtos.size());
            log.info("Distinct plans:{}", planDtos);
            createPlanRates(rawPlanDtos, planDtos, geoLocationDtos);

        }
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor){
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Extract the plan details from the csv file
     * @param csvRecords
     * @return
     */
    private List<RawPlanDto> extractPlanDetails(Iterable<CSVRecord> csvRecords){
        List<RawPlanDto> rawPlanDtos = new ArrayList<>();
        for (CSVRecord record: csvRecords){

            RawPlanDto rawPlanDto = RawPlanDto.builder()
                    .stateTypeCode(record.get("state"))
                    .fipsCode(record.get("fips"))
                    .zipCode(record.get("zip_code"))
                    .planId(record.get("plan_id"))
                    .planName(record.get("plan_name"))
                    .planDescription(record.get("plan_description"))
                    .productTypeCode(record.get("product_type_code"))
                    .age(Integer.parseInt(record.get("age")))
                    .genderTypeCode(record.get("gender"))
                    .premiumRate(BigDecimal.valueOf(Long.parseLong(record.get("rate"))))
                    .build();
            String tobaccoInd = record.get("tobacco_ind");
            if(tobaccoInd.equalsIgnoreCase("yes")){
                rawPlanDto.setTobaccoInd(true);
            }else{
                rawPlanDto.setTobaccoInd(false);
            }
            rawPlanDtos.add(rawPlanDto);
        }
        return rawPlanDtos;
    }

    /**
     * Get and create the distinct geo locations
     * @param rawPlanDtos
     * @return
     */
    private List<GeoLocationDto> getDistinctGeoLoctions(List<RawPlanDto> rawPlanDtos){
        List<GeoLocationDto> geoLocationDtos = new ArrayList<>();
        List<RawPlanDto> distinctGeolocations = rawPlanDtos.stream().filter(distinctByKey(rawPlanDto -> Arrays.asList(rawPlanDto.getStateTypeCode(),
                rawPlanDto.getFipsCode(),
                rawPlanDto.getZipCode()))).toList();
        distinctGeolocations.forEach(rawPlanDto -> {
            geoLocationDtos.add(GeoLocationDto.builder()
                            .stateTypeCode(rawPlanDto.getStateTypeCode())
                            .fipsCode(rawPlanDto.getFipsCode())
                            .zipCode(rawPlanDto.getZipCode())
                    .build());
        });
        // create these locations in the backend and send back the updated geo locations that
        // contains the geo locations with the SK
        return geoLocationService.createGeoLocations(geoLocationDtos);
    }

    /**
     * Get distinct plans from the CSV file
     * @param rawPlanDtos
     * @return
     */
    private List<PlanDto> getDistinctPlans(List<RawPlanDto> rawPlanDtos, List<GeoLocationDto> geoLocationDtos){
        List<PlanDto> planDtos = new ArrayList<>();
        // get all the distinct plans from the csv file
        List<RawPlanDto> distinctPlans = rawPlanDtos.stream()
                .filter(distinctByKey(
                        rawPlanDto -> Arrays.asList(rawPlanDto.getPlanId()))).toList();
        // Create the plan dto objects from the raw plan dto
        distinctPlans.forEach(rawPlanDto -> {
            planDtos.add(PlanDto.builder()
                            .planId(rawPlanDto.getPlanId())
                            .planName(rawPlanDto.getPlanName())
                            .planDescription(rawPlanDto.getPlanDescription())
                            .productTypeCode(rawPlanDto.getProductTypeCode())
                    .build());
        });
        // find the geo-locations associated with each of the plan and add it to the plan dto object
        planDtos.forEach(planDto -> {
            // Get all the locations associated with the plan from the csv file
            List<RawPlanDto> locations = rawPlanDtos.stream()
                    .filter(rawPlanDto -> rawPlanDto.getPlanId().equals(planDto.getPlanId()))
                    .toList();
            Set<GeoLocationDto> geoLocations = new HashSet<>();
            // Find the geo location dto object with the SK so that it can be added to plan
            locations.stream().forEach(rawPlanDto -> {
                GeoLocationDto geoLocation = geoLocationDtos.stream().filter(geoLocationDto -> geoLocationDto.getStateTypeCode().equals(rawPlanDto.getStateTypeCode()) &&
                        geoLocationDto.getFipsCode().equals(rawPlanDto.getFipsCode()) &&
                        geoLocationDto.getZipCode().equals(rawPlanDto.getZipCode())).findFirst().get();
                geoLocations.add(geoLocation);
            });
            planDto.setGeoLocationDtos(geoLocations);
        });
        // todo
        // Create the plans in the back end and then send it back with the sks
        return planService.savePlans(planDtos);
    }

    /**
     * Create the plan rates
     * @param rawPlanDtos
     * @param planDtos
     * @param geoLocationDtos
     */
    private void createPlanRates(List<RawPlanDto> rawPlanDtos, List<PlanDto> planDtos,
                                 List<GeoLocationDto> geoLocationDtos){
        List<PlanRateDto> rateDtos = new ArrayList<>();
        // Create the plan rate dto object
        rawPlanDtos.stream().forEach(rawPlanDto -> {
            PlanDto plan = planDtos.stream()
                    .filter(planDto -> planDto.getPlanId().equals(rawPlanDto.getPlanId()))
                    .findFirst()
                    .get();
            GeoLocationDto geoLocation = geoLocationDtos.stream()
                    .filter(geoLocationDto -> geoLocationDto.getFipsCode().equals(rawPlanDto.getFipsCode()) &&
                            geoLocationDto.getZipCode().equals(rawPlanDto.getZipCode()) &&
                            geoLocationDto.getStateTypeCode().equalsIgnoreCase(rawPlanDto.getStateTypeCode()))
                    .findFirst()
                    .get();
            PlanRateDto planRateDto = PlanRateDto.builder()
                    .planPremiumRate(rawPlanDto.getPremiumRate())
                    .age(rawPlanDto.getAge())
                    .genderTypeCode(rawPlanDto.getGenderTypeCode())
                    .tobaccoInd(rawPlanDto.isTobaccoInd())
                    .planDto(plan)
                    .geoLocationDto(geoLocation)
                    .build();
            rateDtos.add(planRateDto);
        });
        log.info("Distinct plan rate size:{}", rateDtos.size());
//        log.info("Distinct plat rate dtos:{}", dtos);
        // create the plan rates
        planRateService.savePlanRates(rateDtos);
    }
}
