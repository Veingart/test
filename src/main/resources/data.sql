DROP TABLE IF EXISTS author cascade;
DROP TABLE IF EXISTS address cascade;
DROP TABLE IF EXISTS gallery cascade;
DROP TABLE IF EXISTS painting cascade;


CREATE TABLE author (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              name VARCHAR(250) NOT NULL,
                              surname VARCHAR(250) NOT NULL
);

CREATE TABLE address (
                         id INT AUTO_INCREMENT  PRIMARY KEY,
                         location VARCHAR(250) NOT NULL
);

CREATE TABLE gallery (
                         id INT AUTO_INCREMENT  PRIMARY KEY,
                         name VARCHAR(250) NOT NULL,
                         address_id INT,
                         foreign key (address_id) references address(id) ON DELETE CASCADE
);

CREATE TABLE painting (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              title VARCHAR(250) NOT NULL,
                              year INT NOT NULL,
                              gallery_id INT,
                              foreign key (gallery_id) references gallery(id) ON DELETE CASCADE
);
CREATE TABLE painting_author_list (
                          painting_list_id INT,
                          foreign key (painting_list_id) references painting(id) ON DELETE CASCADE,
                          author_list_id INT,
                          foreign key (author_list_id) references author(id) ON DELETE CASCADE
);





INSERT INTO author (name, surname) VALUES
('Anastasia', 'Dayboh'),
('Bill', 'Potter'),
('Jill', 'Perepelko');

INSERT INTO address (location) VALUES
('Minsk, lllll, 23'),
('Grodno, aaaaa, 55'),
('Horki, sdddd, 22');

INSERT INTO gallery (name, address_id) VALUES
('Gallery', 1),
('Gallery 2', 2),
('Gallery 3', 3);

INSERT INTO painting (title, year, gallery_id) VALUES
('Painting', 1999, 2),
('Painting 2', 2000, 3),
('Painting 3', 1978, 1);

INSERT into painting_author_list (painting_list_id, author_list_id) VALUES
(1, 2),
(1, 3),
(2, 1),
(3, 3);
