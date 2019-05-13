# SpringMVCProject

**To run application there is needed enter database settings in "application.properties"** 

**Projects is based on:**
 - Spring hibernate MVC,
 - Spring Data,
 - Spring security,
 - Spring validation,
 - Thymeleaf,
 - Boostrap


**Features:**

 - Login and register forms with security and validation,
 - User roles based on Spring Security (admin role and user role),
 - User role priviliges:
      - Searching free rooms by city name,
      - Checking rooms' details,
      - Booking avaliable rooms by check-in and check-out dates,
      - Checking reservations
 - Admin role extra priviliges:
      - setting all users accounts' statuses (activate/deactivated),
      - checking and adding new rooms      
 - REST api added:
      - http://localhost:8080/api
      - http://localhost:8080/api/room/{id}
      - http://localhost:8080/api/panel/admin/users
      - http://localhost:8080/api/panel/admin/user/{id}/reservations
 - Data.sql file attaches example users:
      - email: admin@mail.pl password: adminpass - with admin role 
      - email: user@mail.pl password: pass - with user role(activated)
      - email: user2@mail.pl password: pass -with user role(deactivated)
      
 **Database model:**

![Alt text](/src/main/resources/static/images/database.PNG?raw=true "Database")

