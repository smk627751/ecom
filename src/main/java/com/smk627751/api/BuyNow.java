package com.smk627751.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smk627751.model.Product;
import com.smk627751.model.User;
import com.smk627751.repository.Repository;

@WebServlet("/BuyNow")
public class BuyNow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		User user = Repository.getInstance().getUser(email);
		String id = request.getParameter("id");
		Product product = Repository.getInstance().getProductById(id);
		request.setAttribute("user", user);
		request.setAttribute("product", product);
		request.getRequestDispatcher("buynow.jsp").forward(request, response);
	}

}
