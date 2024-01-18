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

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String q = request.getParameter("q");
		User user = Repository.getInstance().getUser(email);
		List<Product> products = Repository.getInstance().getProducts(q);
		request.setAttribute("products",products);
		request.setAttribute("user", user);
		request.getRequestDispatcher("home.jsp").forward(request, response);	
	}

}
