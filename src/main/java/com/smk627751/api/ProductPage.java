package com.smk627751.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smk627751.model.Product;
import com.smk627751.repository.Repository;

@WebServlet("/Product")
public class ProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Product product = Repository.getInstance().getProductById(id);
		request.setAttribute("product",product);
		request.getRequestDispatcher("product.jsp").forward(request, response);
	}
}
