DROP TABLE IF EXISTS `PLANCATALOGDB`.`geo_location`;
DROP TABLE IF EXISTS `PLANCATALOGDB`.`plan`;
DROP TABLE IF EXISTS `PLANCATALOGDB`.`plan_rate`;
CREATE TABLE IF NOT EXISTS `PLANCATALOGDB`.`geo_location` (
    `geo_location_sk` VARCHAR(36) NOT NULL,
    `stateTypeCode` VARCHAR(10) NOT NULL,
    `fips_code` VARCHAR(20) NOT NULL,
    `zip_code` VARCHAR(20) NOT NULL,
    `created_date` DATETIME NULL,
    `updated_date` DATETIME NULL,
    PRIMARY KEY (`geo_location_sk`))
    ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `PLANCATALOGDB`.`plan` (
    `plan_sk` VARCHAR(36) NOT NULL,
    `geo_location_sk` VARCHAR(36) NOT NULL,
    `plan_id` VARCHAR(50) NOT NULL,
    `plan_name` VARCHAR(100) NOT NULL,
    `product_type_code` VARCHAR(50) NOT NULL,
    `plan_description` VARCHAR(100) NULL,
    `created_date` DATETIME NULL,
    `updated_date` DATETIME NULL,
    PRIMARY KEY (`plan_sk`),
    INDEX `geo_fk_idx` (`geo_location_sk` ASC) VISIBLE,
    UNIQUE INDEX `plan_id_UNIQUE` (`plan_id` ASC) VISIBLE,
    CONSTRAINT `geo_fk`
    FOREIGN KEY (`geo_location_sk`)
    REFERENCES `PLANCATALOGDB`.`geo_location` (`geo_location_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `PLANCATALOGDB`.`plan_rate` (
    `plan_rate_sk` VARCHAR(36) NOT NULL COMMENT 'The primary key for the table',
    `plan_sk` VARCHAR(36) NOT NULL COMMENT 'The foreign key for the plan table\n',
    `plan_premium_rate` DECIMAL(10,2) NOT NULL COMMENT 'The plan premium rate for the member for enrolling in the plan',
    `age` INT NOT NULL COMMENT 'The age of the member',
    `genderTypeCode` VARCHAR(10) NOT NULL COMMENT 'The gender of the member',
    `tobacco_ind` TINYINT NOT NULL COMMENT 'Identifies if the tobacco indicator is yes or no',
    `created_date` DATETIME NULL COMMENT 'Date when the record was created\n',
    `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
    PRIMARY KEY (`plan_rate_sk`),
    INDEX `fk_plan_rate_plan1_idx` (`plan_sk` ASC) VISIBLE,
    CONSTRAINT `fk_plan_rate_plan1`
    FOREIGN KEY (`plan_sk`)
    REFERENCES `PLANCATALOGDB`.`plan` (`plan_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;