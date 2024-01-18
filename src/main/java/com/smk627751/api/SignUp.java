package com.smk627751.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smk627751.model.User;
import com.smk627751.repository.Repository;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fName = request.getParameter("FirstName");
		String LName = request.getParameter("LastName");
		String email = request.getParameter("Email");
		String pass= request.getParameter("Password");
		String cPass = request.getParameter("ConfirmPassword");
		User user = Repository.getInstance().getUser(email);
		if(user != null)
		{
			request.setAttribute("status", "User already exists");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		if(!pass.equals(cPass))
		{
			request.setAttribute("status", "Password and confirm password doesn't match");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		boolean isAffected = Repository.getInstance().addUser(new User(fName, LName, email, pass));
		if(isAffected)
		{
			response.sendRedirect("signin.jsp");
		}
	}

}
