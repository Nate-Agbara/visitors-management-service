CREATE TABLE staff (
  id BIGINT AUTO_INCREMENT NOT NULL,
   staff_name VARCHAR(255) NOT NULL,
   phone_number VARCHAR(255) NULL,
   email_address VARCHAR(255) NOT NULL,
   address VARCHAR(255) NULL,
   CONSTRAINT pk_staff PRIMARY KEY (id)
);

CREATE TABLE users (
  id BIGINT AUTO_INCREMENT NOT NULL,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE visitors (
  id BIGINT AUTO_INCREMENT NOT NULL,
   visitor_name VARCHAR(255) NOT NULL,
   phone_number VARCHAR(255) NULL,
   email_address VARCHAR(255) NULL,
   address VARCHAR(255) NULL,
   CONSTRAINT pk_visitors PRIMARY KEY (id)
);

CREATE TABLE visits (
  id BIGINT AUTO_INCREMENT NOT NULL,
   visitors_id BIGINT NULL,
   staff_id BIGINT NULL,
   date_of_visit datetime NULL,
   reason_for_visit VARCHAR(255) NULL,
   CONSTRAINT pk_visits PRIMARY KEY (id)
);

ALTER TABLE staff ADD CONSTRAINT uc_staff_email_address UNIQUE (email_address);

ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE visits ADD CONSTRAINT FK_VISITS_ON_STAFF FOREIGN KEY (staff_id) REFERENCES staff (id);

ALTER TABLE visits ADD CONSTRAINT FK_VISITS_ON_VISITORS FOREIGN KEY (visitors_id) REFERENCES visitors (id);

ALTER TABLE users ADD COLUMN date_created DATETIME NULL DEFAULT CURRENT_TIMESTAMP AFTER password;
