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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Hakkımızda

</body>
</html>