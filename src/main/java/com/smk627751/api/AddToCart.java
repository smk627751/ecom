package com.smk627751.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smk627751.repository.Repository;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String productId = request.getParameter("id");
		boolean isAffected = Repository.getInstance().addToCart(email,productId);
		if(isAffected)
		{
			response.sendRedirect(request.getContextPath()+"\\Product?email="+email+"&id="+productId);
		}
	}

}
