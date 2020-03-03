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

import com.cg.DAO.IUserDAO;
import com.cg.DAO.UserDAOImpl;
import com.cg.exceptions.InsuranceException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		IUserDAO userDAO = new UserDAOImpl();
		System.out.println("In login servlet");
		
		
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		RequestDispatcher dispatcher = null;
		System.out.println("value from servlet "+username);
		try {
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			session.setAttribute("token", -1);
			if(userDAO.validate(username, password)==1) {
				String roleCode = userDAO.getRoleCode(username);
				session.setAttribute("role", roleCode);
				if(roleCode.equals("usr")) {
					dispatcher = request.getRequestDispatcher("userPage.jsp");
					dispatcher.forward(request, response);
					
				} else if(roleCode.equals("agnt")) {
					dispatcher = request.getRequestDispatcher("agent.jsp");
					dispatcher.forward(request, response);
					
				}else if(roleCode.equals("adm")) {
					dispatcher = request.getRequestDispatcher("admin.jsp");
					dispatcher.forward(request, response);
					
				}
			}else {
				System.out.println("ygtfy");
			//<h1><font color = 'red'> Invalid Credentials, try again </font></h1>");
				request.setAttribute("error", -1);
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);

			}
		} catch (Exception e) {
			//throw new Exception(password);
			System.out.println("Error while login");
		}
	}

}
