CREATE TABLE IF NOT EXISTS users
(
	id serial PRIMARY KEY,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	email varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS greeting
(
    greetingId serial PRIMARY KEY,
    content varchar(500) NOT NULL
)
