package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cg.DAO.ILoginDAO;
import com.cg.DAO.LoginDAOImpl;
import com.cg.exceptions.InsuranceException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = null;
		if(request.getSession(false).getAttribute("username")!=null) {
			
			String role = request.getSession(false).getAttribute("role").toString();
			if(role.equals("usr")) {
				
				dispatcher = request.getRequestDispatcher("userPage.jsp");
				dispatcher.forward(request, response);
				
			} else if(role.equals("agnt")) {
				
				dispatcher = request.getRequestDispatcher("agent.jsp");
				dispatcher.forward(request, response);
				
			}else if(role.equals("adm")) {
				
				dispatcher = request.getRequestDispatcher("admin.jsp");
				dispatcher.forward(request, response);
				
			}
		}
		dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILoginDAO loginDAO = new LoginDAOImpl();
		
		
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		RequestDispatcher dispatcher = null;
		try {
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			session.setAttribute("token", -1);
			if(loginDAO.validate(username, password)==1) {
				String roleCode = loginDAO.getRoleCode(username);
				session.setAttribute("role", roleCode);
				if(roleCode.equals("usr")) {
					
					if(username.equals(password)) {
						dispatcher = request.getRequestDispatcher("resetPassword.jsp");
						dispatcher.forward(request, response);
					}else {
						LOGGER.info("Inside the login servlet method : Insured");
						dispatcher = request.getRequestDispatcher("userPage.jsp");
						dispatcher.forward(request, response);
					}
					
				} else if(roleCode.equals("agnt")) {
					LOGGER.info("Inside the login servlet method : Handler");
					dispatcher = request.getRequestDispatcher("agent.jsp");
					dispatcher.forward(request, response);
					
				}else if(roleCode.equals("adm")) {
					LOGGER.info("Inside the login servlet method : Adjuster");
					dispatcher = request.getRequestDispatcher("admin.jsp");
					dispatcher.forward(request, response);
					
				}
			}else {
				LOGGER.info("Invalid credentials");
				request.setAttribute("error", -1);
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);

			}
		} catch (Exception e) {
			LOGGER.error("Couldn't Fetch the data from database");
		}
	}

}
