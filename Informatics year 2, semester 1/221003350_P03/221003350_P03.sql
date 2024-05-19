CREATE TABLE Person(
id VARCHAR(9) UNIQUE NOT NULL,
personname VARCHAR(50)NOT NULL,
personsurname VARCHAR(50) NOT NULL,
persontel VARCHAR(11) NOT NULL,
personemail VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO Person(id,personname,personsurname,persontel,personemail)
Select id,personname,personsurname,persontel,personemail
FROM P03DATA;

CREATE TABLE Car(
car_reg VARCHAR(10) UNIQUE NOT NULL,
carbrand VARCHAR(30) NOT NULL,
carmodel VARCHAR(20) NOT NULL,
caryear DATE NOT NULL,
PRIMARY KEY(car_reg)
);

INSERT INTO Car(car_reg,carbrand,carmodel,caryear)
Select car_reg,carbrand,carmodel,caryear
FROM P03DATA;


CREATE TABLE Drive(
car_reg VARCHAR(10) NOT NULL,
id VARCHAR(9) NOT NULL,
FOREIGN KEY (car_reg) REFERENCES CAR,
FOREIGN KEY (id) REFERENCES PERSON,
PRIMARY KEY(car_reg,id)
);

INSERT INTO Drive(car_reg,id)
Select car_reg,id
FROM P03DATA;

SELECT personname,personsurname
FROM Person;

SELECT carbrand
FROM Car;

SELECT personname,personsurname,CAR.car_reg,carbrand
FROM Car,Person,Drive
WHERE Person.id = Drive.id AND Drive.car_reg = CAR.car_reg;

SELECT COUNT(car_reg) AS NumCars
FROM CAR;
