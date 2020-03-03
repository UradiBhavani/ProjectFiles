package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.AdminSearchBasedOnClaimNumberDAOImpl;
import com.cg.DAO.IAdminSearchBasedOnClaimNumberDAO;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/adminSearchBasedOnClaimNumber")
public class AdminSearchBasedOnClaimNumberServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IAdminSearchBasedOnClaimNumberDAO adminSearchBasedOnClaimNumberDAO = new AdminSearchBasedOnClaimNumberDAOImpl();
		
		try {
			String username = (String) request.getSession().getAttribute("username");
			int claimNumber = Integer.parseInt(request.getParameter("claimNumber"));
			List<ShowClaimDetails> claimList = adminSearchBasedOnClaimNumberDAO.getClaimDetails(claimNumber);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("adminShowClaimList.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Error in admin search");
		}
	}
}
