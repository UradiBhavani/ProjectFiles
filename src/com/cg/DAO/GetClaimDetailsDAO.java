package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cg.dto.Claim;
import com.cg.dto.Questions;
import com.cg.exceptions.InsuranceException;
import com.cg.utility.JDBCUtility;

public class GetClaimDetailsDAO implements IGetClaimDetails {
	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public Claim getClaim(int claimNumber) throws Exception {
//		ArrayList<Claim> claimList = new ArrayList<>();
		connection = JDBCUtility.getConnection();
		Claim claim = new Claim();
		try {
			String query = "select *  from claim where CLAIMNUMBER=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, claimNumber);
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				System.out.println("in while loop");
				claim.setClaimNumber(res.getInt(1));
				claim.setClaimReason(res.getString(2));
				claim.setAccidentLocationStreet(res.getString(3));
				claim.setAccidentCity(res.getString(4));
				claim.setAccidentState(res.getString(5));
				claim.setAccidentZip(res.getInt(6));
				claim.setClaimType(res.getString(7));
				claim.setPolicyNumber(res.getInt(8));
				claim.setUserName(res.getString(9));
	//			claimList.add(claim);
			}
		} catch (Exception e) {

			System.out.println("Error in questions creation DAO "+ e.getMessage());
		}
		return claim;
	}

}
