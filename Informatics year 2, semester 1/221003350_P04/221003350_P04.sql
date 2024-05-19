--Query B

CREATE TABLE Game(
gam_Name CHAR(50) NOT NULL UNIQUE,
gam_Date DATE NOT NULL,
gam_MinAge INT NOT NULL,
gam_Price DECIMAL(7,2) NOT NULL,
PRIMARY KEY(gam_Name)
);

CREATE TABLE Customer(
cus_ID CHAR(10) NOT NULL UNIQUE,
cus_Initials CHAR(5) NOT NULL,
cus_Surname CHAR(50) NOT NULL,
cus_Country CHAR(50) NOT NULL,
cus_DOB DATE NOT NULL,
PRIMARY KEY(cus_ID)
);

CREATE TABLE Subscribe(
gam_Name CHAR(50) NOT NULL,
cus_ID CHAR(10) NOT NULL,
sub_Rate INT NOT NULL CHECK(sub_Rate < 6 AND sub_Rate > 0),
FOREIGN KEY(gam_Name) REFERENCES Game,
FOREIGN KEY(cus_ID) REFERENCES Customer,
PRIMARY KEY (gam_Name, cus_ID)
);

CREATE TABLE Hint(
hin_Num INT NOT NULL UNIQUE,
hin_Desc CHAR(150) NOT NULL,
hin_Publish DATE NOT NULL,
hin_Dev CHAR(50) NOT NULL,
gam_Name CHAR(50) NOT NULL,
FOREIGN KEY(gam_Name) REFERENCES Game,
PRIMARY KEY(hin_Num),

);

CREATE TABLE Comment(
cus_ID CHAR(10) NOT NULL,
hin_Num INT NOT NULL,
com_Num INT NOT NULL, --The number of times a customer has commented on a specific hint
com_Desc CHAR(150) NOT NULL,
com_Date DATE NOT NULL,
FOREIGN KEY(cus_ID) REFERENCES Customer,
FOREIGN KEY(hin_Num) REFERENCES Hint,
PRIMARY KEY (cus_ID, hin_Num, com_Num)
);



CREATE VIEW QueryX AS
(SELECT Person.id from Person);

Select * FROM QueryX;

drop VIEW QueryX;





--Query C

INSERT INTO Game VALUES
('FortNights','2000/01/03',13,350.20),
('MinesCraft','2010/02/10',16,900.20);

INSERT INTO Customer VALUES
('0123456789','AB','Sischy','South Africa', '2002/02/10'),
('1123456789','CD','South','USA', '2010/05/09');

INSERT INTO Subscribe VALUES 
('ghbf','1123456789',5),
('ghbf','0123456789',3),
('FortNights','0123456789',3),
('FortNights','1123456789',1);

SELECT * FROM Subscribe

INSERT INTO Hint VALUES
(1,'Inside Triangle','2001/01/03','Jack Right', 'FortNights'),
(2,'Near Tree','2002/01/03','Jack Right', 'FortNights');

INSERT INTO Comment VALUES
('0123456789',1,1,'Was very helpful','2003/01/03'),
('0123456789',1,2,'Was NOT very helpful','2003/02/05');


SELECT Game.gam_Name, Game.gam_Price, AVG(Subscribe.sub_Rate) AS [AVG_Rating]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
GROUP BY Game.gam_Name,Game.gam_Price
--Query D

SELECT *
FROM Game
ORDER BY gam_Price DESC;


--Query E


--where customer has subscribed to both games
Select CUSTOMER.cus_ID
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
WHERE Game.gam_Name = 'FortNights'
INTERSECT
Select CUSTOMER.cus_ID
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
WHERE Game.gam_Name = 'ghbf'


--and
Select CUSTOMER.cus_ID
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
WHERE Game.gam_Name = 'FortNights'
AND CUSTOMER.cus_ID = (
Select CUSTOMER.cus_ID
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
WHERE Game.gam_Name = 'ghbf')


--view
CREATE VIEW game1 AS(
Select CUSTOMER.cus_ID
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
WHERE Game.gam_Name = 'FortNights');

CREATE VIEW game2 AS(
Select CUSTOMER.cus_ID
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
WHERE Game.gam_Name = 'ghbf');

--view
Select cus_ID FROM game2
INTERSECT
Select cus_ID FROM game1

drop table Subscribe



Select CUSTOMER.cus_ID
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
WHERE Game.gam_Name = 'FortNights' AND Subscribe.cus_ID IN(Select cus_ID FROM game2)



SELECT Game.gam_Name,MAX(Subscribe.sub_Rate) AS [MAX]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID

SELECT Game.gam_Name,CUSTOMER.cus_ID, Subscribe.sub_Rate AS [MAX]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
where 
Subscribe.sub_Rate in
(SELECT MAX(Subscribe.sub_Rate) AS [MAXs]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
GROUP BY Game.gam_Name)
AND
Game.gam_Name in
(SELECT Game.gam_Name AS [MAXs]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
GROUP BY Game.gam_Name)


HAVING MAX(Subscribe.sub_Rate) = Subscribe.sub_Rate

SELECT Game.gam_Name,MAX(Subscribe.sub_Rate)
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
GROUP BY Game.gam_Name
----


Select Game.gam_Name
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN Hint
ON Game.gam_Name = Hint.gam_Name
WHERE Game.gam_Name = 'ghbf' AND 'FortNights' in (Select * FROM X)


GROUP BY Game.gam_Name
HAVING count(Game.gam_Name) > 2

Select Game.gam_Name
FROM Game INNER JOIN Subscribe 
ON Game.gam_Name = Subscribe.gam_Name
INTERSECT
Select Game.gam_Name
FROM Game INNER JOIN Hint 
ON Game.gam_Name = Hint.gam_Name;


--Query F

Select Game.gam_Name, Hint.Hin_Desc, Hint.Hin_Num
FROM Game INNER JOIN Hint
ON Game.gam_Name = Hint.gam_Name;



--Query G

Select Customer.cus_ID, Subscribe.gam_Name, Subscribe.sub_Rate
FROM Customer, Subscribe
WHERE Customer.cus_ID = Subscribe.cus_ID;


--Query H
SELECT Game.gam_Name, Game.gam_Price, AVG(Subscribe.sub_Rate) AS [AVG_Rating]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
GROUP BY Game.gam_Name,Game.gam_Price
HAVING AVG(Subscribe.sub_Rate) =
(
SELECT min(AVG_Rating)
FROM
(
Select Game.gam_Name,AVG(Subscribe.sub_Rate) AS [AVG_Rating]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
GROUP BY Game.gam_Name
)sub_Rate
)

SELECT Game.gam_Name, Game.gam_Price, Max(Subscribe.sub_Rate)
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
GROUP BY Game.gam_Name,Game.gam_Price
HAVING Max(Subscribe.sub_Rate) IN
(
SELECT MAX_Rating
FROM
(
Select Game.gam_Name,MAX(Subscribe.sub_Rate) AS [MAX_Rating]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
GROUP BY Game.gam_Name
)sub_Rate
)


SELECT Game.gam_Name,CUSTOMER.cus_ID, Max(Subscribe.sub_Rate) AS [MAX]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
GROUP BY Game.gam_Name,CUSTOMER.cus_ID
HAVING Max(Subscribe.sub_Rate) IN
(
SELECT MAX_Rating
FROM
(
Select Game.gam_Name,MAX(Subscribe.sub_Rate) AS [MAX_Rating]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
GROUP BY Game.gam_Name
)sub_Rate
)
AND Game.gam_Name in
(
SELECT gNAME
FROM
(
Select Game.gam_Name AS [gNAME]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
GROUP BY Game.gam_Name
) gam_Name
)
AND CUSTOMER.cus_ID in(
SELECT Game.gam_Name,CUSTOMER.cus_ID, Max(Subscribe.sub_Rate) AS [MAX]
FROM Game INNER JOIN Subscribe
ON Game.gam_Name = Subscribe.gam_Name
INNER JOIN CUSTOMER
ON CUSTOMER.cus_ID = Subscribe.cus_ID
GROUP BY Game.gam_Name,CUSTOMER.cus_ID)


AND
Max(Subscribe.sub_Rate) in Subscribe.sub_Rate


