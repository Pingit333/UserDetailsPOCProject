# UserDetialsPOCProject

Information :
Plugins : data-jpa, web, devtools, mysql-connector,spring-jdbc
DB connectiity : mysql sever

Progress til :12/03/2021
1. Spring boot stater project created.
2. User Pojo/ Bean class is created.
3. UserController class is created.
3. UserRepository class is created.
4. UserNotFoundException class for handling the exception.

Woking function :
1.retrive all users details.
2.retrive one/multiple user details by requestparam(by name, by surname , by pincode).
3.save/create new data by postman.
4.hard deletion of user (by id).


Progress til :12/06/2021
1. added ResponseEntity Machenism for all methods.
2. added Custom Exception via controllerAdvice to handle exception as entityResponse.
3. added PUT menthod for Updating the details of Existing user.
4. added sort functionaly to sort user data - (By birthdate,By dateOfJoining ,By name, By surname, By Pincode , By ID)
5. Validation added for the user related fileds.

Working Function :
1. Able to sort Data on basis on DOJ & Birthdate
2. Able to modify existing  data.
3. Able to handle Exception.
4. Able to Respond via Response Entity Manchenism.
5. Validation contraints added for the various user filed along with Respose Entity message.

Progress til :12/07/2021
1.Update in Rest URLs as per operation based.
2.added Soft Delete function by adding a boolean tag and bit(1) at database side as column.

Working Function:
1.able to soft delete the user.
