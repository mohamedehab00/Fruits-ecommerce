CREATE TABLE `fruits_ecommerce_db`.`user` (
             `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
             `email` VARCHAR(100) NOT NULL,
             `password` VARCHAR(100) NOT NULL,
             `created_at` TIMESTAMP NOT NULL ,
             `updated_at` TIMESTAMP NOT NULL ,
              PRIMARY KEY (`id`),
              UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
              UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
    ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `fruits_ecommerce_db`.`role` (
             `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
             `type` VARCHAR(45) NOT NULL,
             `created_at` TIMESTAMP NOT NULL ,
             `updated_at` TIMESTAMP NOT NULL ,
              PRIMARY KEY (`id`),
              UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
              UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)
    ENGINE=InnoDB DEFAULT CHARSET=UTF8;
