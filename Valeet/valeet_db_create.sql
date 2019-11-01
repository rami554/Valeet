-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-11-01 21:38:44.258

-- tables
-- Table: val_billing
CREATE TABLE val_billing (
    billing_id int NOT NULL AUTO_INCREMENT,
    booking_id int NOT NULL,
    name varchar(50) NOT NULL,
    nit bigint NOT NULL,
    total_due numeric(10,2) NOT NULL,
    auth_number bigint NOT NULL,
    control_number varchar(50) NOT NULL,
    billing_date date NOT NULL,
    payment_status int NOT NULL,
    CONSTRAINT val_billing_pk PRIMARY KEY (billing_id)
);

-- Table: val_booking
CREATE TABLE val_booking (
    booking_id int NOT NULL AUTO_INCREMENT,
    vehicle_id int NOT NULL,
    garage_id int NOT NULL,
    tariff_id int NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    start_time time NOT NULL,
    end_time time NOT NULL,
    total_time int NOT NULL,
    CONSTRAINT val_booking_pk PRIMARY KEY (booking_id)
);

-- Table: val_garage
CREATE TABLE val_garage (
    garage_id int NOT NULL AUTO_INCREMENT,
    person_id int NOT NULL,
    name varchar(50) NOT NULL,
    lat double(10,6) NOT NULL,
    `long` double(10,6) NOT NULL,
    total_spots int NOT NULL,
    free_spots int NOT NULL,
    occupied_spots int NOT NULL,
    status int NOT NULL,
    CONSTRAINT val_garage_pk PRIMARY KEY (garage_id)
);

-- Table: val_person
CREATE TABLE val_person (
    person_id int NOT NULL AUTO_INCREMENT,
    first_name varchar(50) NOT NULL,
    first_last_name varchar(50) NULL,
    second_last_name varchar(50) NULL,
    email varchar(50) NOT NULL,
    password varchar(30) NOT NULL,
    personal_id varchar(30) NOT NULL,
    parking_admin int NOT NULL,
    status int NOT NULL,
    CONSTRAINT val_person_pk PRIMARY KEY (person_id)
);

-- Table: val_tariff
CREATE TABLE val_tariff (
    tariff_id int NOT NULL AUTO_INCREMENT,
    description varchar(30) NOT NULL,
    rate numeric(10,2) NOT NULL,
    status int NOT NULL,
    CONSTRAINT val_tariff_pk PRIMARY KEY (tariff_id)
);

-- Table: val_vehicle
CREATE TABLE val_vehicle (
    vehicle_id int NOT NULL AUTO_INCREMENT,
    person_id int NOT NULL,
    license_plate varchar(10) NOT NULL,
    status int NOT NULL,
    CONSTRAINT val_vehicle_pk PRIMARY KEY (vehicle_id)
);

-- foreign keys
-- Reference: val_automobile_val_person (table: val_vehicle)
ALTER TABLE val_vehicle ADD CONSTRAINT val_automobile_val_person FOREIGN KEY val_automobile_val_person (person_id)
    REFERENCES val_person (person_id);

-- Reference: val_billing_val_booking (table: val_billing)
ALTER TABLE val_billing ADD CONSTRAINT val_billing_val_booking FOREIGN KEY val_billing_val_booking (booking_id)
    REFERENCES val_booking (booking_id);

-- Reference: val_booking_val_garage (table: val_booking)
ALTER TABLE val_booking ADD CONSTRAINT val_booking_val_garage FOREIGN KEY val_booking_val_garage (garage_id)
    REFERENCES val_garage (garage_id);

-- Reference: val_booking_val_tariff (table: val_booking)
ALTER TABLE val_booking ADD CONSTRAINT val_booking_val_tariff FOREIGN KEY val_booking_val_tariff (tariff_id)
    REFERENCES val_tariff (tariff_id);

-- Reference: val_booking_val_vehicle (table: val_booking)
ALTER TABLE val_booking ADD CONSTRAINT val_booking_val_vehicle FOREIGN KEY val_booking_val_vehicle (vehicle_id)
    REFERENCES val_vehicle (vehicle_id);

-- Reference: val_garage_val_person (table: val_garage)
ALTER TABLE val_garage ADD CONSTRAINT val_garage_val_person FOREIGN KEY val_garage_val_person (person_id)
    REFERENCES val_person (person_id);

-- End of file.

