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
    season VARCHAR(20) NOT NULL,
    series VARCHAR(20) NOT NULL
);

CREATE TABLE account (
    email VARCHAR(40) PRIMARY KEY NOT NULL,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(30) NOT NULL,
    city VARCHAR(20) NOT NULL
);

CREATE TABLE profile (
    name VARCHAR(20) NOT NULL,
    account VARCHAR(40) NOT NULL,
    age SMALLINT NOT NULL
);

CREATE TABLE profile_movie (
    profile_email VARCHAR(40) NOT NULL,
    profile_name VARCHAR(20) NOT NULL,
    movie INT NOT NULL,
    watch_time SMALLINT NOT NULL DEFAULT 0
);

CREATE TABLE profile_episode (
    profile_email VARCHAR(40) NOT NULL,
    profile_name VARCHAR(20) NOT NULL,
    episode INT NOT NULL,
    watch_time SMALLINT NOT NULL DEFAULT 0
);

-- Now add foreign key and primary key constraints
ALTER TABLE episode ADD CONSTRAINT fk_episode_series FOREIGN KEY (series) REFERENCES series;

ALTER TABLE profile ADD PRIMARY KEY (account, name);
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
-- Series
INSERT INTO series (title, genre, language, age_indication) VALUES
('Sherlock', 'Detective', 'Engels', 12),
('Breaking Bad', 'Spanning', 'Engels-Amerikaans', 16),
('Fargo', 'Spanning', 'Engels-Amerikaans', 16);

-- Movies
INSERT INTO movie (id, title, duration, genre, language, age_indication) VALUES
(8001, 'The Life of Brian', 94, 'Humor', 'Engels', 12),
(8002, 'Pulp Fiction', 154, 'Misdaad', 'Engels-Amerikaans', 16),
(8004, 'Pruimebloesem', 80, 'Erotiek', 'Nederlands', 18),
(8008, 'Reservoir Dogs', 99, 'Misdaad', 'Engels-Amerikaans', 16),
(8010, 'The Good, The Bad and the Ugly', 161, 'Western', 'Engels-Amerikaans', 12),
(8011, 'Andy Warhol"s Dracula', 103, 'Humor', 'Engels-Amerikaans', 16),
(8012, 'Ober', 97, 'Humor', 'Nederlands', 6),
(8014, 'Der Untergang', 178, 'Oorlog', 'Duits', 6),
(8016, 'De helaasheid der dingen', 108, 'Humor', 'Vlaams', 12),
(8017, 'A Clockwork Orange', 136, 'SF', 'Engels', 16);

-- Episodes
INSERT INTO episode (id, series, season, episode_number, title, duration) VALUES
(1001, 1, 'Seizoen 1', 1, 'A Study in Pink', 88), -- sherlockss
(1002, 1, 'Seizoen 1', 2, 'The Blind Banker', 88),
(1003, 1, 'Seizoen 1', 3, 'The Great Game', 88),
(1004, 1, 'Seizoen 2', 1, 'A Scandal in Belgravia', 88),
(1005, 1, 'Seizoen 2', 2, 'The Hounds of Baskerville', 88),
(1006, 1, 'Seizoen 2', 3, 'The Reichenbach Fall', 88),
(1007, 1, 'Seizoen 3', 1, 'The Empty Hearse', 88),
(1008, 1, 'Seizoen 3', 2, 'The Sign of Three', 88),
(1009, 1, 'Seizoen 3', 3, 'His Last Vow', 88),
(1010, 1, 'Special', 0, 'The Abominable Bride', 89), -- wasn't sure how to handle this one, application will read episode 0 as none
(2000, 2, 'Seizoen 1', 1, 'Pilot', 58), -- breaking bad
(2001, 2, 'Seizoen 1', 2, 'Cat"s in the Bag...', 48),
(2002, 2, 'Seizoen 1', 3, '...And the Bag"s in the River', 48),
(2003, 2, 'Seizoen 1', 4, 'Cancer Man', 48),
(2004, 2, 'Seizoen 1', 5, 'Gray Matter', 48),
(2005, 2, 'Seizoen 1', 6, 'Crazy Handful of Nothin"', 48),
(2006, 2, 'Seizoen 1', 7, 'A No-Rough-Stuff-Type Deal', 48),
(2007, 2, 'Seizoen 2', 1, 'Seven Thirty-Seven', 48),
(2008, 2, 'Seizoen 2', 2, 'Grilled', 48),
(2009, 2, 'Seizoen 2', 3, 'Bit by a Dead Bee', 48),
(2010, 2, 'Seizoen 2', 4, 'Down', 48),
(2011, 2, 'Seizoen 2', 5, 'Breakage', 48),
(2012, 2, 'Seizoen 2', 6, 'Peekaboo', 48),
(2013, 2, 'Seizoen 2', 7, 'Negro Y Azul', 48),
(2014, 2, 'Seizoen 2', 8, 'Better Call Saul', 48),
(2015, 2, 'Seizoen 2', 9, '4 Days Out', 48),
(2016, 2, 'Seizoen 2', 10, 'Over', 48),
(2017, 2, 'Seizoen 2', 11, 'Mandala', 48),
(2018, 2, 'Seizoen 2', 12, 'Phoenix', 48),
(2019, 2, 'Seizoen 2', 13, 'ABQ', 48),
(3001, 3, 'Seizoen 1', 1, 'The Crocodile"s Dilemma', 68), -- fargo
(3002, 3, 'Seizoen 1', 2, 'The Rooster Prince', 68),
(3003, 3, 'Seizoen 1', 3, 'A Muddy Road', 68),
(3004, 3, 'Seizoen 1', 4, 'Eating the Blame', 68),
(3005, 3, 'Seizoen 1', 5, 'The Six Ungraspables', 68),
(3006, 3, 'Seizoen 1', 6, 'Buridan"s Ass', 68),
(3007, 3, 'Seizoen 1', 7, 'Who Shaves the Barber?', 68),
(3008, 3, 'Seizoen 1', 8, 'The Heap', 68),
(3009, 3, 'Seizoen 1', 9, 'A Fox, a Rabbit, and a Cabbage', 68),
(3010, 3, 'Seizoen 1', 10, 'Morton"s Fork', 68),
(3101, 3, 'Seizoen 2', 1, 'Waiting for Dutch', 68),
(3102, 3, 'Seizoen 2', 2, 'Before the Law', 68),
(3103, 3, 'Seizoen 2', 3, 'The Myth of Sisyphus', 68),
(3104, 3, 'Seizoen 2', 5, 'The Gift of the Magi', 68),
(3105, 3, 'Seizoen 2', 4, 'Fear and Trembling', 68),
(3106, 3, 'Seizoen 2', 6, 'Rhinoceros', 68),
(3107, 3, 'Seizoen 2', 7, 'Did you do this? No, you did it!', 68),
(3108, 3, 'Seizoen 2', 8, 'Loplop', 68),
(3109, 3, 'Seizoen 2', 9, 'The Castle', 68),
(3110, 3, 'Seizoen 2', 10, 'Palindrome', 68);