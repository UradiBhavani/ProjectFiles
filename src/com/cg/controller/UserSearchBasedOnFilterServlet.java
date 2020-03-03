package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.IUserSearchBasedOnFilterDAO;
import com.cg.DAO.UserSearchBasedOnFilterDAOImpl;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/userSearchBasedOnFilter")
public class UserSearchBasedOnFilterServlet extends HttpServlet{
	
	
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
		IUserSearchBasedOnFilterDAO userSearchBasedOnFilter = new UserSearchBasedOnFilterDAOImpl();
		
		try {
			String username = (String) request.getSession().getAttribute("username");
			String filter = (String) request.getParameter("filter");
			List<ShowClaimDetails> claimList = userSearchBasedOnFilter.getClaimDetails(username,filter);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("userShowClaimList.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Error while retriving the user data using filter ");
		}
	}

}
