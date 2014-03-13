<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	/*  String role = request.getParameter("role");
	 String pid = request.getParameter("pid");
	 session.setAttribute("role", role);
	 session.setAttribute("pid", pid);
	 */
	/* out.println(session.getAttribute("pid"));
	out.println(session.getAttribute("role"));
	out.println(session.getAttribute("vps_id")); */

	if (session.getAttribute("pid") != null
			&& !session.getAttribute("pid").toString().isEmpty()) {
%>



<jsp:include page="./includes/header.jsp" />


<jsp:include page="./includes/middle.jsp" />


<jsp:include page="./includes/footer.jsp" />

<%
	} else
		response.sendRedirect("./../index.jsp");
%>