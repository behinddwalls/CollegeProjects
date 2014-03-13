<%@page import="javax.ws.rs.core.Response"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>

<%@page import="com.law.order.controller.LoginController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String email_id = request.getParameter("email_id");
	String password = request.getParameter("password");
	String role = request.getParameter("role");

	LoginController login = new LoginController();

	Response resp = login.retrieveLogin(role, email_id, password);
	JSONObject json = (JSONObject) resp.getEntity();
	int id = json.getInt("pid");
	if (!role.equals("citizen")) {
		String dept = json.getString("dept");
		int dept_id = json.getInt("dept_id");
		int vps_id = json.getInt("vps_id");

		if (id != 0) {
			session.setAttribute("role", role);
			session.setAttribute("pid", id);
			session.setAttribute("dept", dept);
			session.setAttribute("dept_id", dept_id);
			session.setAttribute("vps_id", vps_id);
			out.println("SUCCESS1");

		} else {

			out.println("Invalid Login");
		}
	} else {
		if (id != 0) {
			session.setAttribute("role", role);
			session.setAttribute("uid", id);

			out.println("SUCCESS2");

		} else {

			out.println("Invalid Login");
		}
	}
%>
