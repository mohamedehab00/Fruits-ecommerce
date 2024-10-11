CREATE TABLE `fruits_ecommerce_db`.`product` (
                                              `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                              `name` VARCHAR(100) NOT NULL,
                                              `unit` VARCHAR(100) NOT NULL,
                                              `description` VARCHAR(255) NULL,
                                              `price` DOUBLE UNSIGNED NOT NULL,
                                              `image_path` VARCHAR(255) NULL,
                                              `created_at` TIMESTAMP NOT NULL ,
                                              `updated_at` TIMESTAMP NOT NULL ,
                                              PRIMARY KEY (`id`),
                                              UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                              UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE=InnoDB DEFAULT CHARSET=UTF8;