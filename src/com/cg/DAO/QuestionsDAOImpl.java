package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cg.dto.Questions;
import com.cg.utility.JDBCUtility;

public class QuestionsDAOImpl implements IQuestionsDAO{

	Connection connection = null;
	PreparedStatement statement = null;
	
	
	@Override
	public List<Questions> getQuestions(String policyType) throws Exception {
		List<Questions> questions = new ArrayList<>();
		connection = JDBCUtility.getConnection();
		
		try {
			String query = "select questionId,question from questions where policyType=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, policyType);
			System.out.println("policy type=" +policyType);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				System.out.println("in while loop");
				Questions question = new Questions();
				question.setQuestion(res.getString("question"));
				question.setQuestionId(res.getString("questionId"));
				questions.add(question);
			}
			
			System.out.println("question = "+questions);
		} catch (Exception e) {

			System.out.println("Error in questions creation DAO "+ e.getMessage());
		}
		
		return questions;
	}

}
