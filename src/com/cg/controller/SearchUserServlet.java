package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.AgentClaimCreationDAOImpl;
import com.cg.DAO.IAgentClaimCreationDAO;
import com.cg.dto.Policy;


@WebServlet("/searchUserName")
public class SearchUserServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAgentClaimCreationDAO agentClaimCreationDAO = new AgentClaimCreationDAOImpl();
		
		try {
			System.out.println("search.............");
			String username = (String) request.getSession().getAttribute("username");
			String clientUsername = request.getParameter("clientUsername");
			List<Policy> list = agentClaimCreationDAO.getclaimDetails(username,clientUsername);
			System.out.println("Searched List = "+list);
			
			request.setAttribute("policy", list);
			
			
			request.getRequestDispatcher("agentClaimList.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Exception while displaying the cklaim details of the users under agent ");
		}
	}
}
