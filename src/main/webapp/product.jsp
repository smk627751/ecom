<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.smk627751.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="product.css" type="text/css">
<script src="https://kit.fontawesome.com/28c9b452c3.js" crossorigin="anonymous"></script>
<title>product</title>
</head>
<body>
	<div class="header">
		<div class="title">
			SMK Store
		</div>
		<form class="searchbox" action="Search" method="get">
			<%
			String user = request.getParameter("email");
			out.print("<input name=email value="+user+" hidden>");
			%>
			<input type="text" placeholder="search" name="q">
			<button>
				<i class="fa-solid fa-magnifying-glass"></i>
			</button>
		</form>
		<div class="cart">
			<%
				out.print("<a href="+request.getContextPath()+"/Cart?email="+request.getParameter("email")+">");
			%>
			<i class="fa-solid fa-cart-shopping"></i>
			<%
				out.print("<span>Cart</span>");
				out.print("</a>");
			%>
		</div>
	</div>
	<div>
		<%
			Product product = (Product)request.getAttribute("product");
			out.print("<div class=card>");
			out.print("<div class=thumbnail>");
			out.print("<img src="+product.getImage()+">");
			out.print("</div>");
			out.print("<div class=details>");
			out.print("<span class=name>"+product.getName()+"</span>");
			out.print("<span class=catagory>"+product.getCatagory()+"</span>");
			out.print("<span class=price>$"+product.getPrice()+"</span>");
			out.print("<span>Description</span>");
			out.print("<p class=description>"+product.getDescription()+"</p>");
			out.print("<form action=AddToCart method=post class=addtocart>");
			out.print("<input name=email value="+user+" hidden>");
			out.print("<input name=id value="+product.getId()+" hidden>");
			out.print("<button class=addtocart>Add to cart</button>");
			out.print("</form>");
			out.print("<form action=BuyNow method=post class=addtocart>");
			out.print("<input name=email value="+user+" hidden>");
			out.print("<input name=id value="+product.getId()+" hidden>");
			out.print("<button class=buynow>Buy Now</button>");
			out.print("</form>");
			out.print("<div>");
			out.print("</div>");
		%>
	</div>
</body>
</html>