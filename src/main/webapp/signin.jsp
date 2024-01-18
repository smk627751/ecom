<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>Login</title>
</head>
<body>
    <form action="SignIn" class="container" method="post">
        <h1>Login</h1>
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
            <input id="email" type = "text" name="email" required/>
            <label class="label" for="email">Email</label>
        </div>
        <div class="inputfield">
            <input id="password" type = "password" name="password" required/>
            <label class="label" for="password">Password</label>
        </div>
        <input type="submit" value="Sign in"/>
        <span>Don't have an account? <a href="signup.jsp">sign up</a></span>
    </form>
</body>
</html>