<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>Sign up</title>
</head>
<body>
    <form action="SignUp" class="container signup" method="post">
        <h1>Sign up</h1>
        <% 
        	String status = (String) request.getAttribute("status");
        	if(status != null)
        	{
        		out.print("<span class="+"status"+">");
	        	out.print(status);
	        	out.print("</span>");
        	}
        %>
        <div class="inputfield">
            <input id="fname" type = "text" name="FirstName" required/>
            <label class="label" for="fname">First Name</label>
        </div>
        <div class="inputfield">
            <input id="lname" type = "text" name="LastName" required/>
            <label class="label" for="lname">Last Name</label>
        </div>
        <div class="inputfield">
            <input id="email" type = "email" name="Email" required/>
            <label class="label" for="email">Email</label>
        </div>
        <div class="inputfield">
            <input id="password" type = "password" name="Password" required/>
            <label class="label" for="password">Password</label>
        </div>
        <div class="inputfield">
            <input id="cpass" type = "password" name="ConfirmPassword" required/>
            <label class="label" for="cpass">Confirm password</label>
        </div>
        <input type="submit" value="Sign up"/>
        <span>Already have an account? <a href="signin.jsp">login</a></span>
    </form>
</body>
</html>