# BigThinxProject

Design Document:
I have used [spring boot + mongodb] to create this rest api application. 

1. Visit SPRING INITIALIZR website https://start.spring.io/  to generate maven project. provide group name, artifact and dependencies (web, mongodb). Hit generate project button to download project.

2. Import downloaded project in netbeans.
3. Create new mongodb database on mongolab website https://mlab.com/, create user name and password for created database on website. 
4. Modify application.properties file and provide mongodb database url. 
5. Create new model package and user.java class in model package. Define all fields and respective getter and setters for User.
6. Create Controller package and controller class with url (/api)
7. Create repositories package and new repository interface, create new method to get user by filed name. 
7. AutoWire repository in controller. 
8. Create new Api Error class to return error and debug messages. 
9. Create mapping methods to create new user, get user, get all users, update user and delete user. Use UserRepository, ApiError etc classes to implement and return above methods. 
10. Create TestClass to test respective apis, Use MockMvc to provide http environment. 
11. Right click on project to test all testcases. We can use postman as well.
12. Right click on project to run, test using postman or browser. 
13. Run mvn clean package to run test cases and build jar file of project. This jar file can be deployed on any server. 

Advanced feature: Modify project and implement authentication feature. Work in progress.


Steps to run project-

1.	Clone project in your local box
2.	Run “mvn clean package” to build jar file containing project classes, jar file is created under target folder if all test cases     passed successfully otherwise build will fail. 
3.	Run “java -jar target/[jar-file-name]” , this will start project on 8080 port in local box
4.	If you wish to run project on different port specify different port in application.properties file

All Supported api urls:

1. POST: /api/user 
    This url creates new user in the database and accepts JOSN as given below. Name is required.
    
    {
	    "name": "test",
	    "address": "Address",
	    "dateOfBirth": "YYYY-MM-DD",
	    "description": "test user"
    }
    
    dateOfBirth parameter format should be same as mention in json. If any field is omitted then null value will be saved in databse.
    If User created successfully 200 OK status is returned. 
    If Users alreadt exist in the database 409 CONFLICT status is returned.
    If there is any error while creating user 500 Internal Server Error status is returned.
    
2. GET: /api/users
    This url returns array of all users data present in the databse. 
    If there is any error while processing user list 500 Internal Server Error status is returned.
    
3. GET: /api/user/testUser
    This url returns all deatails of testUser user from database. 
    If user does not exist in database 404 Not Found status is returned.
    If there is any error while fetching user 500 Internal Server Error status is returned.
    
4. PUT: /api/user 
    This url updates existing user in the database and accepts JOSN as given below. Name is required.
    
    {
	    "name": "test",
	    "address": "Address",
	    "dateOfBirth": "YYYY-MM-DD",
	    "description": "test user"
    } 
    
    If User updated successfully 200 OK status is returned. 
    If user does not exist in database 404 Not Found status is returned.
    If there is any error while updating user 500 Internal Server Error status is returned.
    
5. DELETE: /api/user/testUser
    This url deletes [testUser] user from database. 
    If User deleted successfully 200 OK status is returned. 
    If user does not exist in database 404 Not Found status is returned.
