CREATE TABLE IF NOT EXISTS `PERSONS` (
  `identification` VARCHAR(20) NOT NULL,
  `names` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`identification`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `CLIENTS` (
  `id` VARCHAR(36) NOT NULL,
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

CREATE TABLE IF NOT EXISTS `ACCOUNTS` (
  `account_number` VARCHAR(12) NOT NULL,
  `type` VARCHAR(15) NOT NULL,
  `initial_balance` DECIMAL(10,2) NOT NULL DEFAULT 0,
  `status` CHAR(1) NOT NULL DEFAULT 'A',
  `clients_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`account_number`),
  INDEX `fk_ACCOUNTS_CLIENTS1_idx` (`clients_id` ASC) VISIBLE,
  CONSTRAINT `fk_ACCOUNTS_CLIENTS1`
    FOREIGN KEY (`clients_id`)
    REFERENCES `CLIENTS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MOVEMENTS` (
  `id` VARCHAR(36) NOT NULL,
  `movements_date` DATETIME NOT NULL,
  `type` VARCHAR(15) NOT NULL,
  `movements_value` DECIMAL(10,2) NOT NULL DEFAULT 0,
  `balance` DECIMAL(10,2) NOT NULL DEFAULT 0,
  `account_number` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_MOVEMENTS_ACCOUNT_idx` (`account_number` ASC) VISIBLE,
  CONSTRAINT `fk_MOVEMENTS_ACCOUNT`
    FOREIGN KEY (`account_number`)
    REFERENCES `ACCOUNTS` (`account_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;