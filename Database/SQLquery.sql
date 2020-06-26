# Author: Amin Amani

############# Creating Tables #############

DROP TABLE IF EXISTS
    user;
CREATE TABLE IF NOT EXISTS user(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100) NULL,
    last_name VARCHAR(100) NOT NULL,
    gender ENUM('M', 'F'),
    phone VARCHAR(11),
    PRIMARY KEY(id)
); 
DESCRIBE
    user;

DROP TABLE IF EXISTS
    address;
CREATE TABLE IF NOT EXISTS address(
    id INT NOT NULL AUTO_INCREMENT,
    address_type ENUM('billing', 'shipping') NOT NULL,
    address_line_1 VARCHAR(250) NOT NULL,
    address_line_2 VARCHAR(250),
    city VARCHAR(100) NOT NULL,
    province VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    PRIMARY KEY(id)
);
DESCRIBE
    address;
	

DROP TABLE IF EXISTS
    user_address;
CREATE TABLE IF NOT EXISTS user_address(
    id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	address_id INT NOT NULL,
    PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE,
	FOREIGN KEY(address_id) REFERENCES address(id) ON DELETE CASCADE
);
DESCRIBE
    user_address;

	
DROP TABLE IF EXISTS
    product;
CREATE TABLE IF NOT EXISTS product(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(10000) NULL,
    image VARCHAR(250) NOT NULL,
    unit_price DOUBLE(9, 2) NOT NULL,
    PRIMARY KEY(id)
);
DESCRIBE
    product;

DROP TABLE IF EXISTS
    cart;
CREATE TABLE IF NOT EXISTS cart(
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    product_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY(product_id) REFERENCES product(id) ON DELETE CASCADE,
    PRIMARY KEY(id)
);
DESCRIBE
    cart;



	
############# Initial Inserts #############

SET FOREIGN_KEY_CHECKS = 0; 

TRUNCATE table user;
#DELETE FROM user;
INSERT INTO user
(id, username, password, email, first_name, middle_name, last_name, gender, phone)
VALUES
(1, 'amin_01', 'amin_01', 'amin_01@myahoo.com', 'Amin1', 'A', 'Amani1', 'M', 15551101001),
(2, 'amin_02', 'amin_02', 'amin_02@myahoo.com', 'Amin2', 'B', 'Amani2', 'M', 15552201002),
(3, 'amin_03', 'amin_03', 'amin_03@myahoo.com', 'Amin3', 'C', 'Amani3', 'M', 15553301003),
(4, 'amin_04', 'amin_04', 'amin_04@myahoo.com', 'Amin4', 'D', 'Amani4', 'M', 15554401004),
(5, 'amin_05', 'amin_05', 'amin_05@myahoo.com', 'Amin5', 'E', 'Amani5', 'M', 15555501005);

TRUNCATE table address;
#DELETE FROM address;
INSERT INTO address
(id, address_type, address_line_1, address_line_2, city, province, country, postal_code)
VALUES
(1, 'billing', '1010 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B1B 1H1'),
(2, 'shipping', '2020 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B2B 2H2'),
(3, 'billing', '3030 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B3B 3H3'),
(4, 'shipping', '4040 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B4B 4H4'),
(5, 'billing', '5050 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B5B 5H5'),
(6, 'shipping', '6060 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B6B 6H6'),
(7, 'billing', '7070 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B7B 7H7'),
(8, 'shipping', '8080 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B8B 8H8'),
(9, 'billing', '9090 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B9B 9H9'),
(10, 'shipping', '1010 Sherbrook Ave', '', 'Montreal', 'QC', 'CA', 'B1B 1H1');

TRUNCATE table user_address;
#DELETE FROM user_address;
INSERT INTO user_address
(id, user_id, address_id)
VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 1),
(4, 2, 2),
(5, 3, 3),
(6, 3, 4),
(7, 4, 5),
(8, 4, 6),
(9, 5, 7),
(10, 5, 8),
(11, 5, 9),
(12, 5, 10);


TRUNCATE table product;
#DELETE FROM product;
INSERT INTO product
(id, name, description, image, unit_price)
VALUES
(1, 'Samsung S6', 'Flagship cellphone from 2015', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '300.00'),
(2, 'Samsung S6 Edge', 'Another flagship cellphone from 2015', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '350.00'),
(3, 'Samsung S7', 'Flagship cellphone from 2016', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '400.00'),
(4, 'Samsung S7 Edge', 'Another flagship cellphone from 2016', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '450.00'),
(5, 'Samsung S8', 'Flagship cellphone from 2017', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '500.00'),
(6, 'Samsung S8 Edge', 'Another flagship cellphone from 2017', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '550.00'),
(7, 'Samsung S9', 'Flagship cellphone from 2018', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '600.00'),
(8, 'Samsung S9 Edge', 'Another flagship cellphone from 2018', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '650.00'),
(9, 'Samsung S10', 'Flagship cellphone from 2019', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '700.00'),
(10, 'Samsung S10 Edge', 'Another flagship cellphone from 2019', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '750.00'),
(11, 'Samsung S20', 'Flagship cellphone from 2020', 'http://www.aminamani.net/assets/demo/java/img/phone.jpg', '800.00');

TRUNCATE table cart;
#DELETE FROM cart;
INSERT INTO cart
(id, user_id, product_id, quantity)
VALUES
(1, 2, 3, 1),
(2, 4, 10, 2),
(3, 3, 6, 1),
(4, 5, 9, 2),
(5, 5, 8, 1),
(6, 1, 1, 5),
(7, 1, 2, 3);

SET FOREIGN_KEY_CHECKS = 1;

############# Triggers #############

# Convert negative price to zero
DELIMITER $$
CREATE TRIGGER t_negative_price_check BEFORE INSERT ON
    product FOR EACH ROW
BEGIN
    IF NEW.unit_price < 0 THEN
		SET NEW.unit_price = 0.00;
	END IF; 
END $$
DELIMITER ;
