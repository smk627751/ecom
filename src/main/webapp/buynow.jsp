<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.smk627751.model.User"%>
<%@page import="com.smk627751.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Place order</title>
<link rel="stylesheet" href="home.css" type="text/css">
<script src="https://kit.fontawesome.com/28c9b452c3.js" crossorigin="anonymous"></script>
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
			<ul id="options" class="options">
				<li>Profile</li>
				<li>My orders</li>
				<li><a href="signin.jsp">Log out</a></li>
			</ul>
		</div>
	</div>
	<div class="buynow">
		<%
			Product product = (Product)request.getAttribute("product");
			out.print("<div class=product>");
			out.print("<img alt="+product.getCatagory()+" src="+product.getImage()+" class=thumbnail>");
			out.print("<div class=details>");
			out.print("<span>"+product.getName()+"</span>");
			out.print("<span>$"+product.getPrice()+"</span>");
			out.print("</div>");
			out.print("</div>");
		%>
		<form class="form" action="PlaceOrder" method="post">
			<%
				out.print("<input type=text name=email value="+user.getEmail()+" hidden>");
				out.print("<input type=text name=product_id value="+product.getId()+" hidden>");
			%>
			<div class="quantity-box">
				<span>quantity</span>
				<div class="quantity">
				<span id="minus">-</span>
				<input type="number" min= 1 value="1" name="quantity" id="quantity" readonly>
				<span id="plus">+</span>
			</div>
			</div>
			<div class="total">
				<span>total</span>
				<input type="number" id="total" name="price" readonly value="<%= product.getPrice()%>">
			</div>
			<input type="submit" value="Place order">
		</form>
	</div>
	<script type="text/javascript">
		let plus = document.getElementById("plus")
		let minus = document.getElementById("minus")
		let quantity = document.getElementById("quantity")
		let total = document.getElementById("total")
		let initial = total.value
		
		plus.addEventListener("click",() => {
			++quantity.value
			total.value = parseInt(total.value) + parseInt(initial)
		})
		minus.addEventListener("click",() => {
			if(quantity.value > 1)
			{
				--quantity.value
				total.value -= initial
			}
			else
			{
				total.value = initial
			}
		})
	</script>
</body>
</html>