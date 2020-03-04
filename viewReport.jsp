<%@page import="java.util.ArrayList"%>
<%@page import="com.cg.dto.UserResponses"%>
<%@page import="java.util.List"%>
<%@page import="com.cg.dto.Claim"%>
<%@page import="com.cg.DAO.GetClaimDetailsDAO"%>
<%@page import="com.cg.DAO.IGetClaimDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	
		IGetClaimDetails claimDetails = new GetClaimDetailsDAO();
		Claim claim = new Claim();
		
		List<UserResponses> userList = new ArrayList<>();
		
		try {
			
			
			claim = claimDetails.getClaim((int)request.getAttribute("ClaimNumber"));
			userList = claimDetails.getResponses(claim.getClaimNumber());
			
		} catch (Exception e) {
			
			
		}
		
	%>
	<center><h1>Report for Claim</h1></center>
	<table>
		<tr><td>User Name<td><%= claim.getUserName() %>
		<tr><td>Claim Reason<td><%= claim.getClaimReason() %>
		<tr><td>Claim Number<td><%= claim.getClaimNumber() %>
		<tr><td>Location <td><%=claim.getAccidentLocationStreet() %>
		<tr><td>City <td><%= claim.getAccidentCity() %>
		<tr><td>State<td><%= claim.getAccidentState() %>
		<tr><td>Zip<td><%= claim.getAccidentZip() %>
		<tr><td>Policy Type<td><%= claim.getClaimType() %>
		<tr><td>PolicyNumber<td><%= claim.getPolicyNumber() %>
			
	</table>
	
	<h3>User Response</h3>
	<table border="1">
		<%
			for(UserResponses answers: userList){		
		%>
			<tr><td><%= answers.getQuestion()%><td><%= answers.getAnswer() %>
		<%
			}
		%>
	
	</table>
	


</body>
</html>