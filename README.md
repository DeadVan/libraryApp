Web App SpringBoot (Thymeleaf + Bootstrap)
=============================
# LibraryApp
Library Application for Croco

Tools
-------------------
* DB - MySQL
* Build Tool - Maven
* Spring Security
* Lombok
* hibernate
* Thymeleaf
* Bootstrap
Database Configuration
-------------------
1. Open application.properties
2. Changes username and password database based on your local configuration
3. Create a database in MySQL called "librarydb"
4.Photos of my database is in project

How to Build
-------------------
1. Make sure you have internet connection.
2. run project and go to localhost:8080/login
   
ADMIN LOGIN
USERNAME - DeadVan
Password - DadvanA123
-------------------
Users Password
DadvanA123
-------------------
ABOUT APPLICATION
-------------------
application has two home pages: /user-home and /admin-home.
everyone who will register will have "USER" role.
Admin should be added in database manualy with role "ADMIN".
Everyone can register on library app and go to /user-home page, but they dont't have rights to borrow books until admin dosn't give user membership from /admin-home page.
From /admin-home admin can add patrons and books, edit and delete them.
