package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.DAO.IQuestionsDAO;
import com.cg.DAO.QuestionsDAOImpl;
import com.cg.dto.Policy;
import com.cg.dto.Questions;

@WebServlet("/questionsServlet")
public class QuestionsServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IQuestionsDAO questionDAO = new QuestionsDAOImpl();
		RequestDispatcher dispatcher = null;
		
		
		try {
			String policyType= (String) request.getAttribute("policyType");
			List<Questions> list = questionDAO.getQuestions(policyType);
			
			System.out.println("List in servlet"+list);
			request.setAttribute("questionsList", list);
			
			request.getRequestDispatcher("displayQuestion.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
