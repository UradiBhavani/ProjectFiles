package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.AdminShowClaimDetailsDAOImpl;
import com.cg.DAO.AgentShowClaimDetailsDAOImpl;
import com.cg.DAO.IAdminShowClaimDetailsDAO;
import com.cg.DAO.IAgentShowClaimDetailsDAO;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/adminShowClaimDetails")
public class AdminShowClaimDetailsServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAdminShowClaimDetailsDAO adminShowClaimDetailsDAO = new AdminShowClaimDetailsDAOImpl();
		
		try {
			String username = (String) request.getSession().getAttribute("username");
			List<ShowClaimDetails> claimList = adminShowClaimDetailsDAO.getClaimDetails();
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("adminShowClaimList.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
