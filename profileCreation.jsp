<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cg.DAO.AdminInterfaceDAOImpl"%>
<%@page import="com.cg.DAO.IAdminInterfaceDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	
   if(session.getAttribute("username")==null){
	   response.sendRedirect("login.jsp");
   }
   else{
	   if(session.getAttribute("role").equals("usr")){
			response.sendRedirect("userPage.jsp");
		}
		if(session.getAttribute("role").equals("agnt")){
			response.sendRedirect("agent.jsp");
		}
   }
	
%>
  
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/form.css">




<script>
function disablerole(){
	//document.write(document.getElementById("role").value);
	if(document.getElementById("role").value=="agnt" || document.getElementById("role").value=="adm"){
		
		document.getElementById("agentId").disabled=true;
	}
		else{
			document.getElementById("agentId").disabled=false;
		}
}
function invaidAccountNum(){
	
	
	
	var accountnum =document.getElementById("accntnum").value;
	var num = parseInt(accountnum);
	 if(accountnum.length!=9)
		
		{
				if(num.toString().length!=9){
					alert("Please enter valid account number");
					//document.write("asdlijfalksdjflaksjdflksajdi");
					
				}
			
		} 
	
	
	
}
<%
if(request.getAttribute("errormessage")!=null){
	%>
	
	setTimeout(function(){
		
		document.getElementById("errormessage").style.display="none";
	},5000);
	
<% 
}
%>
</script>










</head>
<body>
<div class="navbar">
  <a href="admin.jsp"><img src="images/logo.png" style="width:15%;">Secure Fox</a>
  <a href="about.jsp">About</a>
  <a href="termsAndCondition.jsp">Terms and conditions</a>
  <div class="dropdown">
    <button class="dropbtn">Contact Us
      <img src="drop.png" style="width:15px;height:15px;"></img>
    </button>
    <div class="dropdown-content">
      <a href="#">Helpline Numbers</a>
      <a href="#">Branches</a>
      <a href="#">Mail Us</a>
    </div>
  </div> 
  <a  href="logout">Logout</a>
  <div class="role">

 Welcome Admin<br>
 <i><%=session.getAttribute("username") %> </i> 


 </div>
 </div> 
<% 
if(request.getAttribute("errormessage")!=null){
    	%>
    	
<div id="errormessage" class="errormessage">
    <%=request.getAttribute("errormessage") %>

</div>
<% 
request.setAttribute("errormessage", null);
} %>




<div class="footer">
  <p>Copyright&#9400;</p>
</div>

<div class="claim-creation-form" id="popup">
	    
	     <form action="profileCreation" method="post">
		    <table class="table-edit">
			    <caption class="caption-edit">Profile Creation</caption>		
				<tr><td><input type="text" placeholder="User Name" class="input-edit" name="userName" required></td></tr>
				<tr><td><input type="password" placeholder="Password" class="input-edit" name="password" required></td></tr>
				
				<tr><td><select class="input-edit" name="roleCode" onchange="disablerole()" id="role" required>
				    <option>---Select Role---</option>
				    <option value="usr">usr</option>
				    <option value="agnt">agnt</option>
				    <option value="adm">adm</option>
				
				
				</select></td></tr>
				<tr><td><select class="input-edit" name="agentId" id ="agentId" required>
					<option value="noselection">---Select Agent----</option>
					
					<%
						IAdminInterfaceDAO adminInterfaceDAO = new AdminInterfaceDAOImpl();
						List<String> agentList = new ArrayList<String>();
						
						for(String agent:agentList){
					%>
					
					    <option value=<%=agent %>><%=agent %></option>
					<%
						}
					%>
				
				</select></td></tr>
				<tr><td><input type="number" placeholder="Account Number" class="input-edit" name="accountNumber" onblur="invaidAccountNum()" id="accntnum" required ></td></tr>
                <tr><td><input type="submit" value="Create Profile" class="button-edit"></td></tr>
			</table>
		 </form>


</div>


</body>
</html>