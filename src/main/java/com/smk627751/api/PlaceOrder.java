package com.smk627751.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smk627751.model.Product;
import com.smk627751.model.User;
import com.smk627751.repository.Repository;

@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String product_id = request.getParameter("product_id");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		boolean isAffected = Repository.getInstance().placeOrder(email,quantity,product_id,price);
		User user = Repository.getInstance().getUser(email);
		List<Product> products = Repository.getInstance().getProducts("");
		request.setAttribute("user", user);
		request.setAttribute("products", products);
		if(isAffected)
		{
			request.setAttribute("order_status", "Ordered successfully");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("order_status", "Failed to place order");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}
}
