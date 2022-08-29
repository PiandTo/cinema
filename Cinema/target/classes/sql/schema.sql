DROP SCHEMA if exists Cinemas CASCADE ;

CREATE SCHEMA Cinemas
    CREATE TABLE Cinemas.hall (
        id serial primary key,
        name text,
        number integer,
        availableSeats integer
    );

    CREATE TABLE Cinemas.film (
        id serial primary key,
        title text,
        year varchar (10),
        age integer,
        description text,
        poster text
    );

    CREATE TABLE Cinemas.session (
        id serial primary key,
        date_time timestamp,
        hall_id INT REFERENCES Cinemas.hall(id),
        film_id INT REFERENCES Cinemas.film(id),
        ticket_cost DECIMAL(7,2)
    );

CREATE TABLE Cinemas.users (
       user_id SERIAL PRIMARY KEY,
       first_name  VARCHAR(255) NOT NULL,
       last_name VARCHAR(255) NOT NULL,
       email VARCHAR(100) NOT NULL,
       phone_number VARCHAR(100) NOT NULL,
       password VARCHAR(100) NOT NULL,
       is_admin bool,
       avatar VARCHAR(255)
);

CREATE TABLE Cinemas.messages (
         id serial primary key,
         username text,
         content text,
         type text,
         film_id INT REFERENCES  Cinemas.film(id)
);

CREATE TABLE Cinemas.logs
(
        log_id SERIAL PRIMARY KEY,
        email VARCHAR(100) NOT NULL ,
        ip VARCHAR(100) NOT NULL,
        time VARCHAR(100) NOT NULL,
        date VARCHAR(100) NOT NULL
);