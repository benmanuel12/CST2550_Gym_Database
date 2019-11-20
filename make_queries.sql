--Display all bookings for a client
CREATE VIEW clientbooking
AS SELECT Bookings.* FROM Bookings WHERE clientID = 'C003';

--Display all booking for a trainer
CREATE VIEW trainerbooking
AS SELECT Bookings.* FROM Bookings WHERE trainerID = 'T003';

--Display all bookings for a specific date
CREATE VIEW datebooking
AS SELECT * FROM Bookings WHERE dateBooked = '2019-11-04';

--Display all bookings for a trainer on a specific date
CREATE VIEW trainerdatebooking
AS SELECT * FROM Bookings WHERE dateBooked = '2019-11-04' AND trainerID = 'T003';
