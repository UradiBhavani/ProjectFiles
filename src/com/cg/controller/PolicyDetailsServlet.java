package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.IPolicyDetailsDAO;
import com.cg.DAO.PolicyDetailsDaoImpl;
import com.cg.dto.PolicyDetails;


@WebServlet("/policyDetailsServlet")
public class PolicyDetailsServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IPolicyDetailsDAO policyDetailsDAO = new PolicyDetailsDaoImpl();
		PrintWriter out = null;
		RequestDispatcher dispatcher = null;
		
		try {
			
			System.out.println("In policy details servlet");
			int noOfQuestions = (int) request.getSession().getAttribute(("noOfQuestions"));
			//System.out.println("no = "+noOfQuestions);
			int res = 0;
			for(int i=1;i<=noOfQuestions;i++) {
				String id = "q"+i;
				String ans = ""+i;
			
				PolicyDetails policyDetails = new PolicyDetails();
				policyDetails.setQuestionId((String)request.getSession().getAttribute((id)));
				policyDetails.setAnswer(request.getParameter(ans));
				policyDetails.setClaimNumber((int)request.getSession().getAttribute("claimNumber"));
				policyDetails.setPolicyNumber((int)request.getSession().getAttribute("policyNumber"));
				res += policyDetailsDAO.addPolicyDetails(policyDetails);
				
				System.out.println("Policy details "+i+" = "+ policyDetails);
				
				
			}
			if(res==noOfQuestions) {
				
			}else {
				out.println("<h1><font color = 'red'> Encountered one problem please try again after some time. </font></h1>");
				dispatcher = request.getRequestDispatcher("displayQuestions.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
		}catch(Exception e) {
			System.out.println("Error in policy details servlet ");
			e.printStackTrace();
		}
	}
}
