



use organizer;

create table organizers (
	id  int(3) NOT NULL AUTO_INCREMENT,
	name varchar(120) NOT NULL,
	email varchar(220) NOT NULL,
	venue varchar(120) NOT NULL,
    price varchar(120) NOT NULL,
	PRIMARY KEY (id)
);


SELECT * FROM organizer.organizers;

insert into organizers values (1,"Natasha","nats@gmail.com","mumbai","20000" );
insert into organizers values (2,"Nidhi","nidhi@gmail.com","pune","30000" );
