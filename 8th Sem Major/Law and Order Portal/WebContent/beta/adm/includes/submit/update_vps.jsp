<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.VpsController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int vpsId = Integer.parseInt(request.getParameter("vpsid"));
	String vpsName = request.getParameter("vpsname");
	int subAdmin = Integer.parseInt(request.getParameter("subadmin"));
	String[] pincodelist = request.getParameterValues("pincodelist");
	String[] departmentsList = request
			.getParameterValues("departments");
	String[] officeridList = request.getParameterValues("officerid");

	out.println(vpsName + "  " + subAdmin + "  " + pincodelist.toString() + " "
			+ departmentsList.toString() + "" + officeridList.toString());

	VpsController vpsController = new VpsController();
	Response resp = vpsController.vpsupdate(vpsId, "", vpsName,
			subAdmin, departmentsList, officeridList, pincodelist);

	out.println(resp.getEntity().toString()); 
%>