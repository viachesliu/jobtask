CREATE TABLE IF NOT EXISTS `devices`.`operation_system`
(
    `system_code` TINYINT UNSIGNED NOT NULL,
    `description`           VARCHAR(10)      NOT NULL COLLATE utf8mb4_general_ci UNIQUE,
    PRIMARY KEY (`system_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `devices`.`devices`
(
    `id`                  INT     UNSIGNED NOT NULL AUTO_INCREMENT,
    `created`             DATETIME         NOT NULL DEFAULT now(),
    `updated`             DATETIME         NOT NULL DEFAULT now(),
    `user_id`        VARCHAR(25)      NOT NULL,
    `device_id`        VARCHAR(25)      NOT NULL COLLATE utf8mb4_general_ci UNIQUE,
    `user_data`             varchar(100)       NOT NULL COLLATE utf8mb4_general_ci,
    `operation_system` TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`operation_system`)
        REFERENCES `devices`.`operation_system` (`system_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;