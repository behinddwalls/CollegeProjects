<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="./site/header.jsp" />
<%
	String params = request.getParameter("pgid");
	if (params != null && !params.isEmpty()) {
		if (params.equals("reg")) {
%>
<jsp:include page="./site/register.jsp" />
<%
	} else if (params.equals("user")) {
%>
<jsp:include page="./site/citizen.jsp" />
<%
	} else if (params.equals("upload")) {
%>
<jsp:include page="./site/idupload.jsp" />
<%
	} else if (params.equals("missing")) {
%>
<jsp:include page="./site/missing.jsp" />
<%
	} else if (params.equals("wantedlist")) {
%>
<jsp:include page="./site/wantedlist.jsp" />
<%
	}

	} else {
%>
<%
	if (session.getAttribute("pid") != null
				&& !session.getAttribute("pid").toString().isEmpty())
			response.sendRedirect("./adm/");
		if (session.getAttribute("uid") != null
				&& !session.getAttribute("uid").toString().isEmpty())
			response.sendRedirect("./?pgid=user");
%>
<jsp:include page="./site/home.jsp" />
<%
	}
%>
<jsp:include page="./site/footer.jsp" />