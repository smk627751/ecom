<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.util.List"%>
 <%@page import="com.smk627751.model.User"%>
 <%@page import="com.smk627751.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="home.css" type="text/css">
<script src="https://kit.fontawesome.com/28c9b452c3.js" crossorigin="anonymous"></script>
<title>Your Cart</title>
</head>
<body>
	<div class="header">
		<div class="title">
			SMK Store
		</div>
		<form class="searchbox" action="Search" method="get">
			<%
			User user1 = (User)request.getAttribute("user");
			if(user1 != null)
			{
				out.print("<input name=email value="+user1.getEmail()+" hidden>");
			}
			else
			{
				if (!response.isCommitted()) {
		            response.sendRedirect(request.getContextPath() + "/signin.jsp");
		        }
			}
			%>
			<input type="text" placeholder="search" name="q">
			<button>
				<i class="fa-solid fa-magnifying-glass"></i>
			</button>
		</form>
		<div class="cart">
			<%
				out.print("<a href="+request.getContextPath()+"/Cart?email="+user1.getEmail()+">");
			%>
			<i class="fa-solid fa-cart-shopping"></i>
			<%
				out.print("<span>Cart</span>");
				out.print("</a>");
			%>
		</div>
		<div class="user">
			<%
				out.print("<h3>");
				User user = (User)request.getAttribute("user");
				if(user != null)
				{
					out.print(user.getFirstName());
					out.print("</h3>");
				}
			%>
		</div>
	</div>
	<div class="catalog cartitems">
		<%
			List<Product> products = (List<Product>) request.getAttribute("products");
			if(products != null)
			{
				for(Product product : products)
				{
					out.print("<a href="+request.getContextPath()+"/Product?email="+user.getEmail()+"&id="+product.getId()+">");
		%>
					<div class="card cartitem">
		<% 
					out.print("<img src="+product.getImage()+" class=thumbnail>");
					out.print("<div class=details>");
					out.print("<span class=name>"+product.getName()+"</span>");
					out.print("<span class=price>$"+product.getPrice()+"</span>");
					out.print("<span><a href="+request.getContextPath()+"/RemoveCartItem?email="+user.getEmail()+"&id="+product.getId()+">Remove</a></span>");
					out.print("</div>");
					out.print("</div>");
					out.print("</a>");
				}
			}
		%>
	</div>
</body>
</html>