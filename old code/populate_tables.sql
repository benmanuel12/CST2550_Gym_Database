--Clients Table
--clientID, firstName, lastName
INSERT INTO Clients
VALUES ('C001', 'Lauren', 'Beard');
INSERT INTO Clients
VALUES ('C002', 'Esme', 'Mosley');
INSERT INTO Clients
VALUES ('C003', 'Leyla', 'Hayden');
INSERT INTO Clients
VALUES ('C004', 'Rosie', 'Johnson');
INSERT INTO Clients
VALUES ('C005', 'Angela', 'Terrell');
INSERT INTO Clients
VALUES ('C006', 'Muhammed', 'Craig');
INSERT INTO Clients
VALUES ('C007', 'Anas', 'Holden');
INSERT INTO Clients
VALUES ('C008', 'Albert', 'Carey');
INSERT INTO Clients
VALUES ('C009', 'Tony', 'Richardson');
INSERT INTO Clients
VALUES ('C010', 'Raphael', 'Yates');

--Trainers Table
--trainerID, firstName, lastName
INSERT INTO Trainers
VALUES ('T001', 'Troy', 'Allison');
INSERT INTO Trainers
VALUES ('T002', 'Tom', 'Silva');
INSERT INTO Trainers
VALUES ('T003', 'Mitchell', 'Riggs');
INSERT INTO Trainers
VALUES ('T004', 'Tianna', 'Bauer');
INSERT INTO Trainers
VALUES ('T005', 'Imogen', 'Mckinney');

--Bookings table
--bookingID, clientID, trainerID, date, timeStart, timeEnd, focus
INSERT INTO Bookings
VALUES ('B001', 'C001', 'T002', '2019-11-05', '14:00:00', '16:00:00', 'muscle gain');
INSERT INTO Bookings
VALUES ('B002', 'C002', 'T001', '2019-11-04', '09:00:00', '11:00:00', 'flexibility');
INSERT INTO Bookings
VALUES ('B003', 'C003', 'T004', '2019-11-09', '12:00:00', '14:00:00', 'flexibility');
INSERT INTO Bookings
VALUES ('B004', 'C004', 'T005', '2019-11-10', '12:00:00', '14:00:00', 'weight loss');
INSERT INTO Bookings
VALUES ('B005', 'C005', 'T002', '2019-11-06', '14:00:00', '16:00:00', 'muscle gain');
INSERT INTO Bookings
VALUES ('B006', 'C006', 'T001', '2019-11-05', '09:00:00', '11:00:00', 'weight loss');
INSERT INTO Bookings
VALUES ('B007', 'C007', 'T001', '2019-11-05', '13:00:00', '15:00:00', 'weight loss');
INSERT INTO Bookings
VALUES ('B008', 'C008', 'T002', '2019-11-07', '10:00:00', '12:00:00', 'weight loss');
INSERT INTO Bookings
VALUES ('B009', 'C009', 'T003', '2019-11-07', '09:00:00', '11:00:00', 'flexibility');
INSERT INTO Bookings
VALUES ('B010', 'C010', 'T003', '2019-11-08', '17:00:00', '19:00:00', 'flexibility');