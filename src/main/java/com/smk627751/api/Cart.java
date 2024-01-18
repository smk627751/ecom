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

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		User user = Repository.getInstance().getUser(email);
		List<Product> cartItems = Repository.getInstance().getCartItems(email);
		request.setAttribute("user", user);
		request.setAttribute("products", cartItems);
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
