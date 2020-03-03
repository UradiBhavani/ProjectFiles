package com.cg.DAO;

import java.util.List;

import com.cg.dto.Questions;

public interface IQuestionsDAO {
	List<Questions> getQuestions(String policyType) throws Exception;

}
