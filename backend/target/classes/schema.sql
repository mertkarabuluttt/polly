CREATE TABLE users
(
	userId serial PRIMARY KEY,
	firstName varchar(100) NOT NULL,
	lastName varchar(100) NOT NULL,
	email varchar(100) NOT NULL
);

CREATE TABLE greetings
(
    greetingId serial PRIMARY KEY,
    content varchar(500) NOT NULL
)
