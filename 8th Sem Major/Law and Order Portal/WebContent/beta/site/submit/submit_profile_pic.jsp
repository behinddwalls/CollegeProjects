<%@page import="com.law.order.model.utility.DBUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	out.println(request.getParameter("url"));
int uid = (Integer) session.getAttribute("uid");
String path = request.getParameter("url");
String resp = new DBUtility().updateImagePath("citizen_profile" , path , uid);
out.println(resp);


%>