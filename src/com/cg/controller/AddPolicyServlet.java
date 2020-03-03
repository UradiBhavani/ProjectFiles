package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.AddPolicyDAOImpl;
import com.cg.DAO.IAddPolicyDAO;
import com.cg.dto.Policy;
import com.cg.exceptions.InsuranceException;
import com.cg.utility.JDBCUtility;


@WebServlet("/addPolicyServlet")
public class AddPolicyServlet extends HttpServlet{
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAddPolicyDAO addPolicyDAO = new AddPolicyDAOImpl();
		RequestDispatcher dispatcher=null;
		PrintWriter out = null;
		
		try {
			
			connection = JDBCUtility.getConnection();
			String query = "select max(policyNumber) from policy";
			statement=connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			int policyNumber=0;
			while(res.next()) {
				policyNumber = res.getInt(1);
			}
			
			Policy policy = new Policy();
			policy.setPolicyNumber(++policyNumber);
			//System.out.println("Policy number = "+policyNumber);
			policy.setAccountNumber(Integer.parseInt(request.getParameter("accountNumber")));
			policy.setPolicyPremium(Integer.parseInt(request.getParameter("policyPremium")));
			policy.setPolicyType(request.getParameter("policyType"));
			
			int result = addPolicyDAO.addPolicy(policy);
			if(result==1) {
				dispatcher = request.getRequestDispatcher("admin.jsp");
				dispatcher.forward(request, response);
			}else {
				out.println("<h1><font color = 'red'> Encountered one problem please try again after some time. </font></h1>");
				dispatcher = request.getRequestDispatcher("addPolicy.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (SQLException | InsuranceException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
