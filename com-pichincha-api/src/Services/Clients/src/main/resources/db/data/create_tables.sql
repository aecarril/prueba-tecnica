CREATE TABLE IF NOT EXISTS `PERSONS` (
  `identification` VARCHAR(20) NOT NULL,
  `names` VARCHAR(60) NOT NULL,
  `gender` VARCHAR(15) NOT NULL,
  `age` TINYINT NOT NULL,
  `address` VARCHAR(60) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`identification`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `CLIENTS` (
  `id` VARCHAR(36) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `status` TINYINT NOT NULL DEFAULT 1,
  `persons_identification` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CLIENTS_PERSONS1_idx` (`persons_identification` ASC) VISIBLE,
  CONSTRAINT `fk_CLIENTS_PERSONS1`
    FOREIGN KEY (`persons_identification`)
    REFERENCES `PERSONS` (`identification`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;