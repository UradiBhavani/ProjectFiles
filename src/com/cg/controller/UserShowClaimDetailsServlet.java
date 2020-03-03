package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.IUserShowClaimDetailsDAO;
import com.cg.DAO.UserShowClaimDetailsDAOImpl;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/userShowClaimDetails")
public class UserShowClaimDetailsServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserShowClaimDetailsDAO userShowClaimDetailsDAO = new UserShowClaimDetailsDAOImpl();
		
		try {
			String username = (String) request.getSession().getAttribute("username");
			List<ShowClaimDetails> claimList = userShowClaimDetailsDAO.getClaimDetails(username);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("userShowClaimList.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
