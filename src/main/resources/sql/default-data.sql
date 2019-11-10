create table user (
	id BIGINT not null auto_increment,
	username varchar(255),
	first_name varchar(255),
	last_name varchar(255),
	birth_date date,

	constraint user_pk PRIMARY KEY (id)
 );

 create table post (
 	id BIGINT not null auto_increment,
 	message text,
 	create_date datetime,
 	update_date datetime,
 	user_id BIGINT,

 	constraint post_pk PRIMARY KEY (id)
 );

ALTER TABLE post ADD FOREIGN KEY (user_id) REFERENCES user(id);

insert into user (username, first_name, last_name, birth_date) values
 ('ironman', 'Tony', 'Stark', '1973-05-12'),
 ('captainAmerica', 'Steve', 'Rogers', '1920-01-25'),
 ('spiderman', 'Peter', 'Parcer', '1990-11-07'),
 ('captainMarvel', 'Carol', 'Danvers', '1951-06-17');

 INSERT INTO post(message,create_date, user_id) VALUES
 ('Avengers assemble!', now(), 1),
 ('You cannot justify murder by masking it with a cause', now(), 2),
 ('I\'m not what you think I am.', now(), 4),
 ('With great power, comes great responsibility', now(), 3),
 ('Mr. Stark, I don\'t feel so good', now(), 3);