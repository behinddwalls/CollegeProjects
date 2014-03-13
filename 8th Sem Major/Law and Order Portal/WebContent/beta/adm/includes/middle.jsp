<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("pid") != null
		&& !session.getAttribute("pid").toString().isEmpty()) {
%>
<div id="middle">
	<div id="left-panel">
		<jsp:include page="../utility/left-panel.jsp"></jsp:include>
	</div>
	<div id="right-panel">

		<div id="load"></div>
	</div>


</div>
<%
	}else
	response.sendRedirect("./../");
%>