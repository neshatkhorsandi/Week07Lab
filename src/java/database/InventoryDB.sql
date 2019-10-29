DROP DATABASE if exists InventoryDB;
CREATE DATABASE InventoryDB;

USE InventoryDB;

CREATE TABLE `role_table` (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(25) NOT NULL,
  PRIMARY KEY (`roleID`)
);

INSERT INTO `role_table` VALUES (1, 'system admin');
INSERT INTO `role_table` VALUES (2, 'regular user');
INSERT INTO `role_table` VALUES (3, 'company admin');


CREATE TABLE if not exists user_table (
    email VARCHAR(40) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    fname VARCHAR(20) NOT NULL,
    lname VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    `role` INT(11) NOT NULL,

    CONSTRAINT user_email_pk PRIMARY KEY (email),
    CONSTRAINT `FK_User_Role` FOREIGN KEY (`role`)
        REFERENCES `role_table` (`roleID`) ON DELETE RESTRICT ON UPDATE RESTRICT

);

INSERT INTO user_table (active, email, fname, lname, password, `role`)
    VALUES (TRUE, 'admin@admin.com', 'First', 'Last', 'password', 1);

INSERT INTO user_table (active, email, fname, lname, password, `role`)
    VALUES (TRUE, 'arran.woodruff@sait.edu.ca', 'Arran', 'Woodruff', 'password', 2);

INSERT INTO user_table (active, email, fname, lname, password, `role`)
    VALUES (TRUE, 'david.ward@sait.edu.ca', 'David', 'Ward', 'password', 3);

INSERT INTO user_table (active, email, fname, lname, password, `role`)
    VALUES (TRUE, 'steven.wong01@sait.edu.ca', 'Steven', 'Wong', 'password', 2);

INSERT INTO user_table (active, email, fname, lname, password, `role`)
    VALUES(FALSE, 'alex@gmail.com', 'Alex', 'Carvajal', 'password', 3);

SELECT * FROM user_table;