package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.IReportGenerationDAO;
import com.cg.DAO.ReportGenerationDAOImpl;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/reportGenerationDetails")
public class ReportGenerationServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IReportGenerationDAO reportGenerationDAO = new ReportGenerationDAOImpl();
		
		try {
			String username = (String) request.getSession().getAttribute("username");
			List<ShowClaimDetails> claimList = reportGenerationDAO.getClaimDetails();
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("ReportGenerationScreen.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Error in report generation screen");
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IReportGenerationDAO reportGenerationDAO = new ReportGenerationDAOImpl();
		
		try {
			String username = (String) request.getSession().getAttribute("username");
			String filter = (String) request.getParameter("filter");
			int accNum = Integer.parseInt(request.getParameter("accountNumber"));
			List<ShowClaimDetails> claimList = reportGenerationDAO.getSearchedClaimDetails(accNum, filter);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("ReportGenerationScreen.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Error in report generation screen");
		}
	}
	
	

}
