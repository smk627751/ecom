package com.smk627751.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smk627751.repository.Repository;

@WebServlet("/RemoveCartItem")
public class RemoveCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		boolean isAffected = Repository.getInstance().removeCartItem(email, id);
		if(isAffected)
		{
			response.sendRedirect(request.getContextPath()+"/Cart?email="+email+"");
		}
		else
		{
			response.getWriter().write("Error deleting cart item...");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
