DROP TABLE IF EXISTS `PLANCATALOGDB`.`plan`;
CREATE TABLE IF NOT EXISTS `PLANCATALOGDB`.`plan` (
    `plan_sk` VARCHAR(36) NOT NULL,
    `plan_id` VARCHAR(50) NOT NULL,
    `plan_name` VARCHAR(100) NOT NULL,
    `plan_description` VARCHAR(100) NULL,
    `product_type` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`plan_sk`))
    ENGINE = InnoDB;