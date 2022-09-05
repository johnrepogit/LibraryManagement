CREATE DATABASE IF NOT exists library_mgmt;
use library_mgmt;

CREATE TABLE IF NOT EXISTS `user` (
 `id` INT auto_increment,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `active` INT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `book` (
 `id` INT auto_increment,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `cart` (
 `id` INT auto_increment,
  `user_id` INT,
  `status` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_cart_user` (`user_id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE IF NOT EXISTS `cart_item` (
 `id` INT auto_increment,
  `book_id` INT,
  `cart_id` INT,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
   KEY `idx_cart_item_book` (`book_id`),
  KEY `idx_cart_item_cart` (`cart_id`),
  CONSTRAINT `fk_cart_item_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `fk_cart_item_product` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
);


CREATE TABLE IF NOT EXISTS `reservation` (
 `id` INT auto_increment,
  `user_id` INT,
  `cart_id` INT,
  `status` varchar(255) NOT NULL,
  `booking_number` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
   KEY `idx_cart_ruser` (`user_id`),
  CONSTRAINT `fk_cart_ruser` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  KEY `idx_cart_item_rcart` (`cart_id`),
  CONSTRAINT `fk_cart_item_rcart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
);
