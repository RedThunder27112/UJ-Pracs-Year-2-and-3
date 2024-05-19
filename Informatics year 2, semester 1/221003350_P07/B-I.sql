
-------------------------------
--B
-------------------------------
CREATE TABLE District(
dis_ID CHAR(10) UNIQUE NOT NULL,
dis_Name CHAR(50) NOT NULL,
PRIMARY KEY (dis_ID),
);

CREATE TABLE Segment(
seg_ID CHAR(10) UNIQUE NOT NULL,
seg_Name CHAR(50) NOT NULL,
dis_ID CHAR(10) NOT NULL,
PRIMARY KEY (seg_ID),
FOREIGN KEY (dis_ID) REFERENCES District
);

CREATE TABLE Hospital(
hos_ID CHAR(10) UNIQUE NOT NULL,
hos_Name CHAR(50) NOT NULL,
hos_Number CHAR(10) NOT NULL,
seg_ID CHAR(10) NOT NULL,
PRIMARY KEY (hos_ID),
FOREIGN KEY (seg_ID) REFERENCES Segment
);

CREATE TABLE Doctor(
doc_ID CHAR(10) UNIQUE NOT NULL,
doc_Name CHAR(50) NOT NULL,
doc_Surname CHAR(50) NOT NULL,
doc_License CHAR(50) NOT NULL,
hos_ID CHAR(10) NOT NULL,
PRIMARY KEY (doc_ID),
FOREIGN KEY (hos_ID) REFERENCES Hospital
);


CREATE TABLE Patient(
pat_ID CHAR(10) UNIQUE NOT NULL,
pat_IDNumber CHAR(13) UNIQUE NOT NULL, --while not a primary key, you can not have multiple ones of the same ID NUMBER
pat_Name CHAR(50) NOT NULL,
pat_Surname CHAR(50) NOT NULL,
pat_Age INT NOT NULL,
PRIMARY KEY (pat_ID),
);

CREATE TABLE CovidTest(
cov_ID CHAR(10) UNIQUE NOT NULL,
cov_Rating CHAR(2) NOT NULL,
PRIMARY KEY (cov_ID),
);


CREATE TABLE HospitalVisit(
vis_ID CHAR(10) UNIQUE NOT NULL,
pat_ID CHAR(10) NOT NULL,
doc_ID CHAR(10) NOT NULL,
vis_Date DATE,
cov_ID CHAR(10), --can be null if doesnt have covid
PRIMARY KEY (vis_ID),
FOREIGN KEY (doc_ID) REFERENCES Doctor,
FOREIGN KEY (pat_ID) REFERENCES Patient,
FOREIGN KEY (cov_ID) REFERENCES CovidTest
);
-------------------------------
--C
-------------------------------
INSERT INTO District VALUES
('disID1','disName1'),
('disID2','disName2');

INSERT INTO SEGMENT VALUES
('segID1','segName1','disID1'),
('segID2','segName2','disID1'),
('segID3','segName3','disID1'),
('segID4','segName4','disID2');

INSERT INTO Hospital VALUES
('hosID1','hosName1','hosNum1','segID1'),
('hosID2','hosName2','hosNum2','segID2');

INSERT INTO Doctor VALUES
('docID1','docName1','docSName1','docLic1','hosID1'),
('docID2','docName2','docSName2','docLic2','hosID1'),
('docID3','docName3','docSName3','docLic3','hosID2'),
('docID4','docName4','docSName4','docLic4','hosID2');


INSERT INTO CovidTest VALUES
('covID10','6'),
('covID1','0'),
('covID2','10'),
('covID3','8'),
('covID4','5'),
('covID5','5'),
('covID6','5'),
('covID7','5'),
('covID8','5'),
('covID9','5');


INSERT INTO Patient VALUES
('patID1','patIDNum1','PatName1','PatSName1',10),
('patID2','patIDNum2','PatName2','PatSName2',20);

INSERT INTO HospitalVisit(vis_ID,pat_ID,doc_ID,vis_Date) VALUES
('visID1','patID1','docID1','2000-11-05');

INSERT INTO HospitalVisit VALUES
('visID11','patID2','docID3','2000-05-15','covID10'),
('visID2','patID1','docID2','2000-05-13','covID3'),
('visID3','patID1','docID2','2000-05-12','covID1'),
('visID4','patID2','docID3','2000-05-13','covID2'),
('visID5','patID2','docID3','2000-05-14','covID4'),
('visID6','patID2','docID3','2000-05-15','covID5'),
('visID7','patID2','docID3','2000-05-16','covID6'),
('visID8','patID2','docID3','2000-05-17','covID7'),
('visID9','patID2','docID3','2000-05-18','covID8'),
('visID10','patID2','docID3','2000-05-19','covID7');

-------------------------------
--D
-------------------------------
SELECT *
FROM Hospital;

-------------------------------
--E
-------------------------------
SELECT Doctor.doc_Name
FROM Doctor INNER JOIN HospitalVisit
ON Doctor.doc_ID = HospitalVisit.doc_ID
INNER JOIN PATIENT
ON PATIENT.pat_ID = HospitalVisit.pat_ID
INNER JOIN CovidTest
ON CovidTest.cov_ID = HospitalVisit.cov_ID
WHERE cov_Rating >= 7

-------------------------------
--F
-------------------------------
SELECT Hospital.hos_Name, COUNT(Hospital.hos_Name) AS [NumChecksDone]
FROM Doctor INNER JOIN HospitalVisit
ON Doctor.doc_ID = HospitalVisit.doc_ID
INNER JOIN PATIENT
ON PATIENT.pat_ID = HospitalVisit.pat_ID
INNER JOIN CovidTest
ON CovidTest.cov_ID = HospitalVisit.cov_ID
INNER JOIN Hospital
ON Hospital.hos_ID = Doctor.hos_ID
GROUP BY Hospital.hos_Name

-------------------------------
--G
-------------------------------
SELECT Doctor.doc_Name, COUNT(Doctor.doc_Name) AS [NumChecksDoneLower4]
FROM Doctor INNER JOIN HospitalVisit
ON Doctor.doc_ID = HospitalVisit.doc_ID
INNER JOIN PATIENT
ON PATIENT.pat_ID = HospitalVisit.pat_ID
INNER JOIN CovidTest
ON CovidTest.cov_ID = HospitalVisit.cov_ID
GROUP BY Doctor.doc_Name, cov_Rating
HAVING cov_Rating <= 4


---or

SELECT Doctor.doc_Name, COUNT(Doctor.doc_Name) AS [NumChecksDoneLower4]
FROM Doctor INNER JOIN HospitalVisit
ON Doctor.doc_ID = HospitalVisit.doc_ID
INNER JOIN PATIENT
ON PATIENT.pat_ID = HospitalVisit.pat_ID
INNER JOIN CovidTest
ON CovidTest.cov_ID = HospitalVisit.cov_ID
where cov_Rating <= 4
GROUP BY Doctor.doc_Name


-------------------------------
--H
-------------------------------

SELECT Hospital.hos_Name, COUNT(cov_Rating) AS [NumChecksDone]
FROM Doctor INNER JOIN HospitalVisit
ON Doctor.doc_ID = HospitalVisit.doc_ID
INNER JOIN PATIENT
ON PATIENT.pat_ID = HospitalVisit.pat_ID
INNER JOIN CovidTest
ON CovidTest.cov_ID = HospitalVisit.cov_ID
INNER JOIN Hospital
ON Hospital.hos_ID = Doctor.hos_ID
GROUP BY Hospital.hos_Name, cov_Rating, pat_Age
HAVING cov_Rating >= 5 AND pat_Age >= 18

--or -- the below is more correct
SELECT Hospital.hos_Name, COUNT(DISTINCT PATIENT.pat_ID) AS [NumChecksDone]
FROM Doctor INNER JOIN HospitalVisit
ON Doctor.doc_ID = HospitalVisit.doc_ID
INNER JOIN PATIENT
ON PATIENT.pat_ID = HospitalVisit.pat_ID
INNER JOIN CovidTest
ON CovidTest.cov_ID = HospitalVisit.cov_ID
INNER JOIN Hospital
ON Hospital.hos_ID = Doctor.hos_ID
where  cov_Rating >= 5 AND pat_Age >= 18
GROUP BY Hospital.hos_Name
-------------------------------
--I
-------------------------------

SELECT Hospital.hos_Name,COUNT(Doctor.hos_ID) AS [NumDoctors]
FROM Doctor INNER JOIN Hospital
ON Hospital.hos_ID = Doctor.hos_ID
GROUP BY Hospital.hos_Name
HAVING Hospital.hos_Name IN
(
SELECT Hospital.hos_Name
FROM Doctor INNER JOIN HospitalVisit
ON Doctor.doc_ID = HospitalVisit.doc_ID
INNER JOIN PATIENT
ON PATIENT.pat_ID = HospitalVisit.pat_ID
INNER JOIN CovidTest
ON CovidTest.cov_ID = HospitalVisit.cov_ID
INNER JOIN Hospital
ON Hospital.hos_ID = Doctor.hos_ID
GROUP BY Doctor.doc_Name, cov_Rating,Hospital.hos_Name
HAVING cov_Rating >= 5 AND COUNT(Doctor.doc_Name) > 5
)