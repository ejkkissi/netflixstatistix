-- This query creates the necessary tables and inserts the default data

-- Clear the database for safety reasons
DROP TABLE IF EXISTS profile_movie;
DROP TABLE IF EXISTS profile_episode;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS episode;
DROP TABLE IF EXISTS series;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS account;

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
    account VARCHAR(20) NOT NULL,
    age SMALLINT NOT NULL
);

CREATE TABLE profile_movie (
    profile_email VARCHAR(20) NOT NULL,
    profile_name VARCHAR(20) NOT NULL,
    movie INT NOT NULL,
    watch_time SMALLINT NOT NULL DEFAULT 0
);

CREATE TABLE profile_episode (
    profile_email VARCHAR(20) NOT NULL,
    profile_name VARCHAR(20) NOT NULL,
    episode INT NOT NULL,
    watch_time SMALLINT NOT NULL DEFAULT 0
);

-- Now add foreign key and primary key constraints
ALTER TABLE episode ADD CONSTRAINT fk_episode_series FOREIGN KEY (series) REFERENCES series;

ALTER TABLE profile ADD PRIMARY KEY (email, name);
ALTER TABLE profile ADD CONSTRAINT fk_profile_account FOREIGN KEY (account) REFERENCES account ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE profile_movie ADD PRIMARY KEY (profile_email, profile_name, movie);
ALTER TABLE profile_movie ADD CONSTRAINT fk_pm_movie FOREIGN KEY (movie) REFERENCES movie;
ALTER TABLE profile_movie ADD CONSTRAINT fk_pm_email FOREIGN KEY (profile_email) REFERENCES profile(account) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE profile_movie ADD CONSTRAINT fk_pm_name FOREIGN KEY (profile_name) REFERENCES profile(name) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE profile_episode ADD PRIMARY KEY (profile_email, profile_name, episode);
ALTER TABLE profile_episode ADD CONSTRAINT fk_pe_episode FOREIGN KEY (episode) REFERENCES episode;
ALTER TABLE profile_episode ADD CONSTRAINT fk_pe_email FOREIGN KEY (profile_email) REFERENCES profile(account) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE profile_episode ADD CONSTRAINT fk_pe_name FOREIGN KEY (profile_name) REFERENCES profile(name) ON DELETE CASCADE ON UPDATE CASCADE;

-- Unique constraints
ALTER TABLE profile_movie ADD CONSTRAINT unique_pm UNIQUE (profile_email, profile_name, movie);
ALTER TABLE profile_episode ADD CONSTRAINT unique_pe UNIQUE (profile_email, profile_name, episode);

-- Check for watch_time
ALTER TABLE profile_movie ADD CONSTRAINT check_pm CHECK (watch_time >= 0 AND watch_time <= 100);
ALTER TABLE profile_episode ADD CONSTRAINT check_pe CHECK (watch_time >= 0 AND watch_time <= 100);

-- Last but not least: Inserting data...

