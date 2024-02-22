CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `category_description` text,
  `parent_category_id` int DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `parent_category_id` (`parent_category_id`),
  CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`parent_category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `categories` VALUES (1,'Electronics','Electronics and Gadgets',NULL,'https://example.com/electronics.jpg'),(2,'Fashion','Clothing and Accessories',NULL,'https://example.com/fashion.jpg'),(3,'Mobiles','Smartphones and Tablets',1,'https://example.com/mobiles.jpg'),(4,'Laptops','Notebooks and Ultrabooks',1,'https://example.com/laptops.jpg'),(5,'Shirts','Men and Women Shirts',2,'https://example.com/shirts.jpg'),(6,'Ramraj panchakancha ','this is traditional indian achakam',2,NULL),(7,'Smart Watches','these are smart watches ',1,NULL);


CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `customers` VALUES (1,'customer','john','john@outlook.com','$2a$10$c.jlzL7/EhF6BDs8DlIAB.NDCnajck1DTrI7waw6XCQSua7OI/zve'),(2,'sam','curron','sam.curron@gmail.com','password123'),(4,'tom','curron','tom.curron@gmail.com','password123'),(6,'daniel','sams','daniel.sams@gmail.com','password123'),(7,'Naruto','Uzumaki','buyer@onlineauction.com','password123'),(8,'sameer','ganduri','sameer.ganduri@gmail.com','password123');

CREATE TABLE `sellers` (
  `seller_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `seller_rating` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`seller_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `sellers` VALUES (1,'John','Wic','seller@onlineauction.com','password123',4.50),(2,'mitchell','johnson','mitchell.johnson@gmail.com','password123',0.00),(3,'jason','royaaa','jason.roy@gmail.com','password123',0.00);

CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `description` text,
  `starting_price` decimal(10,2) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id` (`category_id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `products` VALUES (4,'Samsung Galaxy S21','The latest Samsung Galaxy flagship phone with amazing camera and powerful hardware',1000.00,'https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',1,1),(6,'Product1','This is an example product descrip',49.99,'https://example.com/product-image.jpg',NULL,1),(7,'Product8','This is an example product description.',49.99,'https://example.com/product-image.jpg',NULL,1),(8,'Product7','This is an example phone description.',49.99,'https://example.com/product-image.jpg',NULL,2),(9,'Product8','This is an example phone description.',49.99,NULL,NULL,2),(10,'Product8','This is an example phone description.',49.99,NULL,NULL,NULL),(21,'Fitbit Versa','Refurbished fitbit watch versa',10000.00,'https://www.fitbit.com/global/content/dam/fitbit/global/pdp/devices/versa-3/images/desktop/fall21-features-cover.jpg',7,NULL),(22,'Fitbit Versa','new',1000.00,'https://www.fitbit.com/global/content/dam/fitbit/global/pdp/devices/versa-3/images/desktop/fall21-features-cover.jpg',7,NULL),(23,'Fitbit Versa','new add',1000.00,'https://www.fitbit.com/global/content/dam/fitbit/global/pdp/devices/versa-3/images/desktop/fall21-features-cover.jpg',7,1);


CREATE TABLE `auctions` (
  `auction_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `auction_start_time` datetime NOT NULL,
  `auction_end_time` datetime NOT NULL,
  `auction_status` varchar(255) NOT NULL,
  PRIMARY KEY (`auction_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `auctions_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `auctions` VALUES (6,4,'2023-04-13 13:40:00','2023-04-14 20:11:00','Y');

CREATE TABLE `bids` (
  `bid_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `bid_amount` decimal(10,2) NOT NULL,
  `bid_time` datetime NOT NULL,
  `status` enum('Y','N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`bid_id`),
  KEY `product_id` (`product_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `bids_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `bids_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `bids` VALUES (1,4,1,100.00,'2023-04-09 15:01:04','N'),(2,4,7,1000.00,'2023-04-13 19:54:23','N'),(3,4,7,1001.00,'2023-04-13 20:01:22','N');

CREATE TABLE `payments` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `payment_method` varchar(255) NOT NULL,
  `payment_status` varchar(255) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `product_id` (`product_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `payments` VALUES (1,4,7,'UPI','Y'),(2,4,7,'aa','Y'),(3,4,7,'aa','Y'),(4,4,7,'aa','Y'),(5,4,7,'UPI','Y'),(6,6,7,'UPI','Y');

CREATE TABLE `shipping` (
  `shipping_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `shipping_address` text NOT NULL,
  `shipping_method` varchar(255) NOT NULL,
  PRIMARY KEY (`shipping_id`),
  KEY `product_id` (`product_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `shipping_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `shipping_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `invoices` (
  `invoice_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `seller_id` int NOT NULL,
  `product_id` int NOT NULL,
  `invoice_date` datetime NOT NULL,
  `invoice_amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `customer_id` (`customer_id`),
  KEY `seller_id` (`seller_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `invoices_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `invoices_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`seller_id`),
  CONSTRAINT `invoices_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `oauth_clients` (
  `ID` bigint NOT NULL,
  `CLIENT_ID` varchar(255) DEFAULT NULL,
  `CLIENT_SECRET` varchar(255) DEFAULT NULL,
  `ACCESS_TOKEN_VALIDITY_SECONDS` int DEFAULT NULL,
  `REFRESH_TOKEN_VALIDITY_SECONDS` int DEFAULT NULL,
  `AUTHORIZED_GRANT_TYPES` varchar(255) DEFAULT NULL,
  `REGISTERED_REDIRECT_URI` varchar(255) DEFAULT NULL,
  `AUTHORITIES` varchar(255) DEFAULT NULL,
  `ADDITIONAL_INFORMATION` varchar(255) DEFAULT NULL,
  `RESOURCE_IDS` varchar(255) DEFAULT NULL,
  `SCOPED` int DEFAULT NULL,
  `SCOPES` varchar(255) DEFAULT NULL,
  `SECRET_REQUIRED` int DEFAULT NULL,
  `AUTO_APPROVE` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `oauth_clients` VALUES (1,'onlineauction','$2a$10$yzrBr597aM3bKM1.aVTZVev3bcXnLeiEPuvxZ/I5rpTmOMoVNqb8u',3600,86400,'password,authorization_code,refresh_token,client_credentials',NULL,NULL,NULL,'1',1,'read,write',1,1);


