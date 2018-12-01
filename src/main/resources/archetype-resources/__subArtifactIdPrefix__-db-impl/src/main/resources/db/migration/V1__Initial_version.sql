CREATE TABLE users (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  login varchar(100) NOT NULL,
  email varchar(120) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) DEFAULT NULL,
  sex varchar(10) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_login (login),
  UNIQUE KEY UK_email (email),
);