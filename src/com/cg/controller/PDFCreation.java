package com.cg.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.GetClaimDetailsDAO;
import com.cg.DAO.IGetClaimDetails;
import com.cg.dto.Claim;
import com.cg.dto.ShowClaimDetails;

@WebServlet("/pdfCreation")
public class PDFCreation extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String claimNumber = (String)request.getParameter("claimNum");
		int claimN = Integer.parseInt(claimNumber);
		System.out.println("claim number "+ claimNumber);
		IGetClaimDetails claimDetails = new GetClaimDetailsDAO();
		Claim claim = new Claim();
		try {
			claim = claimDetails.getClaim(claimN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("list from claim table"+claim);
		ArrayList<ShowClaimDetails> array = (ArrayList<ShowClaimDetails>)request.getSession().getAttribute("pdfList");
		ReportPage rpage = new ReportPage(); 
		//System.out.println(array.size());
		for(ShowClaimDetails details: array) {
			if(details.getClaimNumber()==Integer.parseInt(claimNumber)) {
				//System.out.println(details.toString());
				rpage.createPage(details, claim);
			}
		}
	}
	
}
