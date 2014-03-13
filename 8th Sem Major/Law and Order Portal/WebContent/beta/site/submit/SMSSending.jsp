<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.law.order.service.SMSSender"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (request.getParameter("ver_code")!=null) {
			if (session.getAttribute("recaptcha").equals(
					request.getParameter("ver_code"))) {

				session.removeAttribute("recaptcha");
				session.removeAttribute("mobile");
				
				out.println("<script type=\"text/javascript\">window.location=\"../../index.jsp\";</script>");
			}
			else
			{
				
			out.println("code mismatch");	
			}
		}
	%>

	<form method="post" action="SMSSending.jsp">
		<input type="text" name="ver_code" />
		 <input type="submit" name="submit" />
	</form>

</body>
</html>