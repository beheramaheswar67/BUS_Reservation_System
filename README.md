** BUS Ticket Reservation System **

Description: 
            The BUS Ticket Reservation System is a Java-based application developed using JDBC for managing bus ticket bookings.
            This project allows users to book, cancel, and view their reservations.
            The system interacts with a MySQL database to store and retrieve reservation details.

Features:
* Book bus tickets for available routes
* View all bookings
* Find Seat Number
* Update Booking
* Cancel Ticket
* Exit

Technologies Used:
* Java JDK (Java Development Kit)
* JDBC (Java Database Connectivity)
* MySQL (Database)
* IDE: IntelliJ IDEA

 Installation & Setup
Clone the repository:
bash
Copy code
git clone https://github.com/your-username/bus-ticket-reservation-system.git
cd bus-ticket-reservation-system

Database Setup:

Install MySQL and create a database.
Execute the SQL script provided in the /db folder to create the necessary tables and populate initial data.
Example:

sql
Copy code
CREATE DATABASE bus_reservation_db;
USE bus_reservation_db;

-- Create tables and insert initial data here
Update Database Configuration:

Update your database connection details in the project’s configuration file (e.g., db.properties):
properties
Copy code
db.url=jdbc:mysql://localhost:3306/bus_reservation_db
db.username=yourUsername
db.password=yourPassword
Compile & Run:

Use your preferred Java IDE to open and run the project.
Alternatively, compile using the command line:
bash
Copy code
javac -cp .:path/to/mysql-connector-java.jar Main.java
java -cp .:path/to/mysql-connector-java.jar Main




