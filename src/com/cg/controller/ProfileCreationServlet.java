package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;

import com.cg.DAO.IProfileCreationDAO;
import com.cg.DAO.ProfileCreationDAOImpl;
import com.cg.dto.UserRole;
@WebServlet("/profileCreation")
public class ProfileCreationServlet extends HttpServlet{
	
		
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IProfileCreationDAO profileCreationDAO = new ProfileCreationDAOImpl();
		
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher=null;
		
		
		
		try {
			
			UserRole userRole = new UserRole();
			userRole.setUserName(request.getParameter("userName"));
			userRole.setPassword(request.getParameter("password"));
			userRole.setRoleCode(request.getParameter("roleCode"));
			userRole.setAgentId(request.getParameter("agentId"));
			userRole.setAccountNumber(Integer.parseInt(request.getParameter("accountNumber")));
			
			
			
			
			
			int id = profileCreationDAO.createProfile(userRole);
			if(id==1) {
					/*
				
						Successfully created a profile.
					
					*/
					
			} 
			else {
				
				
				out.println("<h1><font color = 'red'> Encountered one problem please try again after some time. </font></h1>");
				dispatcher = request.getRequestDispatcher("loginErrorPage.html");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Profile Creation exception "+e);
		}
	}
}
