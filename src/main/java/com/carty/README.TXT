NOTES ON THIS PROJECT:
=====================
In this application, I have created two book controllers for practice only. One uses the JPA repository and the other the 
CRUD repository and have base endpoints "books" and "books2" respectively.

A common problem with using PrePost annotations on controllers is that Spring method security is based on Spring AOP, 
which is by default implemented with JDK proxies. That means that it works fine on the service layer which is injected 
in the controller layer as interfaces, but it is ignored on controller layer because controller generally do not implement 
interfaces. The preferred way is to move the pre-post annotations to the service layer.

Note:
=====
The pre/post annotations are not affected by:
1. Whether we use CRUD vs JPA repository
2. Whether we use an interface with abstract methods on which the service implementation is based.
   In other words, we can go directly to the service implementation without implementing an interface.
   
USING THIS APPLICATION
======================

Although anyone can signup to use this service, a client is not able to use the system services until they have been assigned USER and/or ADMIN roles. 
Hence, there is a pre-supposition that the users of this application will be assigned such roles. This can be done either by:
A. Extending the functionality of this application to alow that to be done by an admin person, OR
B. Manually editing the USER_ROLES table in the backend MYSQL database to ensure that the record exists for the user in question:
    [userID, RoleID] 
    
 eg [13, 1]  to make user 13 an admin
    [13, 2] to make user 13 a basic user
    
    You may put both records for any user who should also be an admin.
      
Step 1: Sign up by supplying (method = POST) a username, password, salary and age in a JSON object to the endpoint "http://localhost:8080/authenticate".
	To do this, use Postman or a similar application. In the Header, use 'no-auth' authorization, content-type 'application/json'  and place the JSON object 
	in the method body of the request like so: 
	{
	 "username":"paul",
 	 "password":"1234",
 	 "salary":15000,
 	 "age":43
 	}
 	
 STEP 2: Get a JWT Security token by sending (method = POST) a JSON object with your name and password the end point "localhost:8080/token/generate-token".
 	Again, in the Header, use 'no-auth' authorization, content-type 'application/json'  and place the JSON object 
	in the method body of the request like so: 
	{
	 "username":"paul",
 	 "password":"1234"
 	 }
 	 
 STEP 3: The result of step 2 is that you will receive a JWT Security token in the body of the response to your generate-token request. Use that token to make
 	requests from the various endpoints supported by the application. BE sure to take note of the http methods that are supported by each endpoint.
 	See the User Controller, Book Controller and Book2 Controller for information on the supported endpoints. In the Header, use 'no-auth' authorization, 
 	Content-type 'application/json' and Authorization = "Bearer "+[your security token]. with  and place the JSON object in the method body of the request like so: 
	Some for some of the endpoints supporting POST and PUT methods, you would be required to send a book object in the body of the request like so: 