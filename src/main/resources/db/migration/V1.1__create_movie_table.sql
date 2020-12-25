CREATE TABLE movies (
  id int NOT NULL,
  title varchar(45) DEFAULT NULL,
  category varchar(45) DEFAULT NULL,
  starrating double DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
);