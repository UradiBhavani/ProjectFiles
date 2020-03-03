package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.AgentSearchBasedOnFilterDAOImpl;
import com.cg.DAO.IAgentSearchBasedOnFilterDAO;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/agentSearchBasedOnFilter")
public class AgentSearchBasedOnFilterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IAgentSearchBasedOnFilterDAO agentSearchBasedOnFilterDAO = new AgentSearchBasedOnFilterDAOImpl();
		
		
		
		try {
			String username = (String) request.getSession().getAttribute("username");
			String filter = (String) request.getParameter("filter");
			int accNum = Integer.parseInt(request.getParameter("accountNumber"));
			List<ShowClaimDetails> claimList = agentSearchBasedOnFilterDAO.getClaimDetails(username,filter,accNum);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("agentShowClaimList.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Error while retriving the agent filter data");
		}
	}

}
