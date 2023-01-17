<%@ page import="com.SupplyChainManagementProject.Core.DbConnection.PostgreSqlDbConnection" %>
<%@ page import="com.SupplyChainManagementProject.Model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>

<% User auth= (user) request.getSession().getAttribute("auth"); 
	if(auth!=null){
		request.setAttribute("auth",auth);
	}
%>
<!DOCTYPE html>
<html>
<head>

<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<% out.print(PostgreSqlDbConnection.getConnection()); %>
<%@include file="includes/footer.jsp" %>
</body>
</html>