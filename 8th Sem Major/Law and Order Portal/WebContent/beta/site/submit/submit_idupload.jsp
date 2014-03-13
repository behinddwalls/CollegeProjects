<%@page import="com.law.order.model.utility.DBUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	out.println(request.getParameter("url"));
    int citizen_id = (Integer)session.getAttribute("citizen_id");
    String path = request.getParameter("url");
    String resp = new DBUtility().updateImagePath("reg" , path , citizen_id);
    out.println(resp);
%>