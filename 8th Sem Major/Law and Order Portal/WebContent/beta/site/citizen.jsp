<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link rel="stylesheet" type="text/css" href="./includes/css/citizen.css" />
<script type="text/javascript" src="./includes/js/jquery.js"></script>
<script type="text/javascript" src="./includes/js/script.js"></script>
<script type="text/javascript">
	
</script>
</head>

<%
	String uid = String.valueOf(session.getAttribute("uid"));

	//if (uid != null && !uid.isEmpty()) {
	if (session.getAttribute("uid") != null
			&& !session.getAttribute("uid").toString().isEmpty()) {
%>

<div id="citizen">
	<div class="htmltabs">
		<!-- The tabs -->
		<ul class="tabs">
			<li class="tab1"><a class="tab1 tab"> Profile </a>
			</li>
			<li class="tab2"><a class="tab2 tab"> Create Complain </a>
			</li>
			<li class="tab3"><a class="tab3 tab"> Check Complain Status
			</a>
			</li>
			<li class="tab4"><a class="tab4 tab"
				href="./adm/includes/logout.jsp"> Logout </a>
			</li>

		</ul>
		<!-- tab 1 -->
		<div class="tab1 tabsContent">
			<div>

				<jsp:include page="./profile.jsp" />


			</div>
		</div>
		<!-- end of t1 -->
		<!-- tab 2 -->
		<div class="tab2 tabsContent">
			<div><jsp:include page="./createcomplain.jsp" /></div>
		</div>
		<!-- end of t2 -->
		<!-- tab 3 -->
		<div class="tab3 tabsContent">
			<div><jsp:include page="./complainstatus.jsp" />
			</div>
		</div>
		<!-- end of t3 -->


	</div>
	<!-- tabbed ends here-->




</div>

<%
	} else {
		out.println("<script>window.location='./'</script>");

	}
%>