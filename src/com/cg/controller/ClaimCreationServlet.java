package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cg.DAO.UserClaimCreationDAOImpl;
import com.cg.DAO.IUserClaimCreationDAO;
import com.cg.dto.Claim;
import com.cg.dto.Policy;
import com.cg.utility.JDBCUtility;

@WebServlet("/claimCreation")
public class ClaimCreationServlet extends HttpServlet{
	
	
	//IClaimCreationDAO claimCreationDAO=null;
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserClaimCreationDAO userClaimCreationDAO=null;
		//= new ClaimCreationDAOImpl();

		try {
			
			userClaimCreationDAO= new UserClaimCreationDAOImpl();

			String username = (String) request.getSession().getAttribute("username");
			System.out.println(username);
			List<Policy> list = userClaimCreationDAO.getclaimDetails(username);
			System.out.println("List = "+list);
			
			request.setAttribute("policy", list);
			
			//System.out.println("Policy type : "+request.getParameter("policyType"));
			request.getRequestDispatcher("claimList.jsp").forward(request, response);
			
			
			
		} catch (Exception e) {
			System.out.println("Error while retriving the claim creation details");
		}
		
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//IClaimCreationDAO claimCreationDAO=null;
		PrintWriter out = null ;
		IUserClaimCreationDAO userClaimCreationDAO=null;
		int claimNumber = 0;
		RequestDispatcher dispatcher = null;
		try {
			connection = JDBCUtility.getConnection();
			String query = "select max(claimNumber) from claim";
			statement=connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				claimNumber = res.getInt(1);
			}

			userClaimCreationDAO= new UserClaimCreationDAOImpl();
			Claim claim = new Claim();
			claim.setClaimReason(request.getParameter("claimReason"));
			
			claim.setAccidentLocationStreet(request.getParameter("location"));
			claim.setAccidentState(request.getParameter("state"));
			claim.setAccidentCity(request.getParameter("accidentCity"));
			claim.setAccidentZip(Integer.parseInt(request.getParameter("zipCode")));
			claim.setClaimType(request.getParameter("policyType"));
			claim.setPolicyNumber(Integer.parseInt(request.getParameter("policyNumber")));
			claim.setClaimNumber(++claimNumber);			
			claim.setUserName(request.getSession().getAttribute("username").toString());
			claim.setStatus();
			
			
			int id = userClaimCreationDAO.registerClaim(claim);
			
			HttpSession session = request.getSession();
			session.setAttribute("policyNumber", Integer.parseInt(request.getParameter("policyNumber")));
			session.setAttribute("claimNumber", claimNumber);
			
			
			if(id==1) {
				request.setAttribute("policyType", request.getParameter("policyType"));
				System.out.println("In dispatcher loop");
				dispatcher = request.getRequestDispatcher("questionsServlet");
				dispatcher.forward(request, response);
			}else {
				out.println("Error occured in the servlet");
			}
			
		}catch (Exception e) {
			System.out.println("Error while retriving the claim creation DAO details"+e.getMessage());
		}
		
	}

}
