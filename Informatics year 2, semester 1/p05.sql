CREATE TABLE Category(
cat_ID CHAR(10) UNIQUE NOT NULL,
cat_Name CHAR(50) NOT NULL,
PRIMARY KEY(cat_ID)
);

CREATE TABLE Product(
pro_ID CHAR(10) UNIQUE NOT NULL,
pro_Name CHAR(50) NOT NULL,
pro_Manufacturer CHAR(50) NOT NULL,
pro_Price NUMERIC(10,2) NOT NULL,
cat_ID CHAR(10) NOT NULL,
PRIMARY KEY(pro_ID),
FOREIGN KEY(cat_ID) REFERENCES Category
);

CREATE TABLE Client(
cli_ID char(10) UNIQUE NOT NULL,
cli_Name char(50),
cli_Surname char(50),
PRIMARY KEY(cli_ID)
);

CREATE TABLE Employee(
emp_ID char(10) UNIQUE NOT NULL,
PRIMARY KEY(emp_ID)
);

CREATE TABLE SalesAgent(
emp_ID char(10) UNIQUE NOT NULL,
PRIMARY KEY(emp_ID),
FOREIGN KEY(emp_ID) REFERENCES Employee,
);

CREATE TABLE ProductManager(
emp_ID char(10) UNIQUE NOT NULL,
cat_ID CHAR(10) NOT NULL,
PRIMARY KEY(emp_ID),
FOREIGN KEY(emp_ID) REFERENCES Employee,
FOREIGN KEY(cat_ID) REFERENCES Category,
);


CREATE TABLE Invoice(
inv_ID char(10) UNIQUE NOT NULL,
cli_ID char(10) NOT NULL,
emp_ID char(10) NOT NULL,
PRIMARY KEY(inv_ID),
FOREIGN KEY(cli_ID) REFERENCES Client,
FOREIGN KEY(emp_ID) REFERENCES Employee,
);

CREATE TABLE Line(
inv_ID char(10) NOT NULL,
pro_ID char(10) NOT NULL,
lin_Price Numeric(10,2) NOT NULL,
lin_Quantity int NOT NULL,
PRIMARY KEY(inv_ID,pro_ID),
FOREIGN KEY(inv_ID) REFERENCES Invoice,
FOREIGN KEY(pro_ID) REFERENCES Product,
);



INSERT INTO Category VALUES
('0000000001','GPUs'),
('0000000002','CPUs'),
('0000000003','Mother Boards'),
('0000000004','Power Supplies'),
('0000000005','Cables');

INSERT INTO Product VALUES
('0000000011','cpu I7','Intel',8000,'0000000002'),
('0000000022','cpu I5','Intel',5000,'0000000002'),
('0000000033','Lan cable','BobsBuilders',50.50,'0000000005'),
('0000000044','Titan 3080','NVIDIA',25000,'0000000001'),
('0000000055','Fibre cable','BobsBuilders',99.99,'0000000005');

INSERT INTO Client VALUES
('0000000111','Bob','West'),
('0000000222','Paul','North'),
('0000000333','David','East'),
('0000000444','Sally','South'),
('0000000555','Tom','Brown');

INSERT INTO Employee VALUES
('0000001111'),
('0000002222'),
('0000003333'),
('0000004444'),
('0000005555'),
('0000006666'),
('0000007777'),
('0000008888'),
('0000009999'),
('0000010000');

INSERT INTO SalesAgent VALUES
('0000001111'),
('0000002222'),
('0000003333'),
('0000004444'),
('0000005555');


INSERT INTO ProductManager VALUES
('0000006666','0000000001'),
('0000007777','0000000002'),
('0000008888','0000000003'),
('0000009999','0000000004'),
('0000010000','0000000005');


INSERT INTO Invoice VALUES
('0000011111','0000000111','0000001111'),
('0000022222','0000000222','0000001111'),
('0000033333','0000000222','0000002222'),
('0000066666','0000000222','0000002222'),
('0000077777','0000000333','0000002222'),
('0000044444','0000000444','0000003333'),
('0000055555','0000000555','0000004444');


INSERT INTO Line VALUES
('0000011111','0000000011',8000,8),
('0000022222','0000000011',8000,5),
('0000033333','0000000033',50.50,1),
('0000033333','0000000022',5000,99),
('0000033333','0000000044',25000,3);


select *
from Category;
select *
from Product;
select *
from Client;
select *
from Employee;
select *
from SalesAgent;
select *
from ProductManager;
select *
from invoice;
select *
from Line;
