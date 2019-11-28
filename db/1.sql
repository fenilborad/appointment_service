SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

CREATE TYPE weekdays as ENUM(
    'SUN',
    'MON',
    'TUE',
    'WED',
    'THU',
    'FRI',
    'SAT'
);

CREATE TABLE doctor_appointment_status (
    id bigserial primary key,
    doctor_code varchar(40) NOT NULL,
    status_name varchar(20) NOT NULL,
    display_name varchar(50) NOT NULL,
    weight int NOT NULL,
    color varchar(8) NOT NULL,
    inserted_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    inserted_by varchar(40) NOT NULL,
    updated_by varchar(40) NOT NULL,
    UNIQUE (doctor_code, status_name)
);

CREATE TABLE appointment (
    id bigserial primary key,
    unique_code varchar(20) NOT NULL,
    patient_code varchar(40) NOT NULL,
    doctor_code varchar(40) NOT NULL,
    appointment_date date NOT NULL,
    starttime time NOT NULL,
    endtime time NOT NULL,
    duration integer NOT NULL,
    status_id integer references doctor_appointment_status(id) NOT NULL,
    inserted_at timestamp,
    updated_at timestamp,
    UNIQUE (appointment_date, starttime)
);

CREATE table doctor_appointment_config(
    doctor_code varchar(40) primary key,
    compulsory boolean default false,
    open_from time with time zone,
    open_to time NOT NULL,
    open_days weekdays[] NOT NULL,
    do_off boolean default false
);
