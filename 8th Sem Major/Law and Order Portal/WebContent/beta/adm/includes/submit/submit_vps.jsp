<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.VpsController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String vpsName = request.getParameter("vpsname");
	int subAdmin = Integer.parseInt(request.getParameter("subadmin"));
	String[] pincodelist = request.getParameterValues("pincodelist");
	String[] departmentsList = request
			.getParameterValues("departments");
	String[] officeridList = request.getParameterValues("officerid");

	out.println(vpsName + "  " + subAdmin + "  " + pincodelist[0]);

	VpsController vpsController = new VpsController();
	Response resp = vpsController.vpscreate("", vpsName, subAdmin,
			departmentsList, officeridList, pincodelist);

	out.println(resp.getEntity().toString());
%>