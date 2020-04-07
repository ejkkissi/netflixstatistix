-- This query creates the necessary tables and inserts the default data

-- Create tables
CREATE TABLE movie (
    id IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    duration INT NOT NULL,
    genre VARCHAR(20) NOT NULL,
    language VARCHAR(20) NOT NULL,
    age_indication SMALLINT
);

CREATE TABLE series (
    id IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    genre VARCHAR(20) NOT NULL,
    language VARCHAR(20) NOT NULL,
    age_indication SMALLINT NOT NULL
);

CREATE TABLE episode (
    id IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    duration INT NOT NULL,
    episode_number SMALLINT NOT NULL,
    season SMALLINT NOT NULL,
    series INT NOT NULL
);

CREATE TABLE account (
    email VARCHAR(20) PRIMARY KEY NOT NULL,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(30) NOT NULL,
    city VARCHAR(20) NOT NULL
);

CREATE TABLE profile (
    name VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    age SMALLINT NOT NULL
);

CREATE TABLE profile_movie (
    profile_email VARCHAR(20) NOT NULL,
    profile_name VARCHAR(20) NOT NULL,
    watch_time SMALLINT NOT NULL DEFAULT 0
);

-- Now add foreign key and primary key constraints