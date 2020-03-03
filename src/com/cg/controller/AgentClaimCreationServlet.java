package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cg.DAO.AgentClaimCreationDAOImpl;
import com.cg.DAO.IAgentClaimCreationDAO;
import com.cg.DAO.IUserClaimCreationDAO;
import com.cg.DAO.UserClaimCreationDAOImpl;
import com.cg.dto.Claim;
import com.cg.dto.Policy;
import com.cg.utility.JDBCUtility;


@WebServlet("/agentClaimCreation")
public class AgentClaimCreationServlet extends HttpServlet{
	
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAgentClaimCreationDAO agentClaimCreationDAO = new AgentClaimCreationDAOImpl();
		
		try {
			String username = (String) request.getSession().getAttribute("username");
			List<Policy> list = agentClaimCreationDAO.getclaimDetails(username);
			System.out.println("List = "+list);
			
			request.setAttribute("policy", list);
			
			
			request.getRequestDispatcher("agentClaimList.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Exception while displaying the cklaim details of the users under agent ");
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//IClaimCreationDAO claimCreationDAO=null;
		PrintWriter out = null ;
		IAgentClaimCreationDAO agentClaimCreationDAO=null;
		int claimNumber = 0;
		RequestDispatcher dispatcher = null;
		try {
			System.out.println("In agent claim creation servlet");
			connection = JDBCUtility.getConnection();
			String query = "select max(claimNumber) from claim";
			statement=connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				claimNumber = res.getInt(1);
			}

			
			System.out.println("Hellllll");
			
			String queryForUserName = "select username from userrole where accountnumber=(select accountnumber from policy where policynumber=?)"; 
			statement=connection.prepareStatement(queryForUserName);
			statement.setString(1, request.getParameter("policyNumber"));
			res = statement.executeQuery();
			
			String username="";
			
			while(res.next()) {
				username = res.getString(1);
			}
			
			System.out.println("username = "+username);
			
			
			agentClaimCreationDAO= new AgentClaimCreationDAOImpl();
			Claim claim = new Claim();
			claim.setClaimReason(request.getParameter("claimReason"));
			
			claim.setAccidentLocationStreet(request.getParameter("location"));
			claim.setAccidentState(request.getParameter("state"));
			claim.setAccidentCity(request.getParameter("city"));
			claim.setAccidentZip(Integer.parseInt(request.getParameter("zipCode")));
			claim.setClaimType(request.getParameter("policyType"));
			claim.setPolicyNumber(Integer.parseInt(request.getParameter("policyNumber")));
			claim.setClaimNumber(++claimNumber);			
			claim.setUserName(username);
			claim.setStatus();
			
			
			int id = agentClaimCreationDAO.registerClaim(claim);
			
			HttpSession session = request.getSession();
			session.setAttribute("policyNumber", Integer.parseInt(request.getParameter("policyNumber")));
			session.setAttribute("claimNumber", claimNumber);
			
			if(id==1) {
				request.setAttribute("policyType", request.getParameter("policyType"));
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
