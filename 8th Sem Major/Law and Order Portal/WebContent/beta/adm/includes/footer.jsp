<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (session.getAttribute("pid") != null
		&& !session.getAttribute("pid").toString().isEmpty()) {

%>
<div id="footer">

About

</div>




</div>

<!-- ----------------Custom PopUp -->
<div id="customPopup">

</div>
<div id="backShadow">
<img src="./media/close.gif" style="position:fixed;top:0;right:0;height:40px;width:40px;" />
</div>
<%

}else
response.sendRedirect("./../");

%>