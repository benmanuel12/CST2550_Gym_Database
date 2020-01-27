CREATE DATABASE gymBookings;
USE gymBookings;

CREATE TABLE Clients(
clientID VARCHAR(4),
firstName VARCHAR(20) NOT NULL,
lastName VARCHAR(20) NOT NULL,
PRIMARY KEY (clientID)
);

CREATE TABLE Trainers(
trainerID VARCHAR(4),
firstName VARCHAR(20) NOT NULL,
lastName VARCHAR(20) NOT NULL,
PRIMARY KEY (trainerID)
);

CREATE TABLE Bookings(
bookingID VARCHAR(4),
clientID VARCHAR(4),
trainerID VARCHAR(4),
dateBooked DATE,
timeStart TIME,
timeEnd TIME,
focus ENUM('weight loss', 'muscle gain', 'flexibility'),
PRIMARY KEY (bookingID),
FOREIGN KEY (clientID) REFERENCES Clients(clientID),
FOREIGN KEY (trainerID) REFERENCES Trainers(trainerID)
);
