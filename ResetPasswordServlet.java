package com.cg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.Http11AprProcessor;
import org.apache.log4j.Logger;

import com.cg.DAO.ILoginDAO;
import com.cg.DAO.LoginDAOImpl;

import oracle.net.aso.l;

public class ResetPasswordServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILoginDAO loginDAO = new LoginDAOImpl();
		RequestDispatcher dispatcher = null;
		
		try {
			String newPassword = request.getParameter("newPassword");
			String username = (String) request.getSession().getAttribute("username");
			
			if(loginDAO.resetPassword(username, newPassword) == 1) {
				dispatcher = request.getRequestDispatcher("userPage.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
