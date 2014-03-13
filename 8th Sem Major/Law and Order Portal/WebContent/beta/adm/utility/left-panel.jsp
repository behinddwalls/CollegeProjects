<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>

<script type="text/javascript" src="./includes/js/leftpanel.js"></script>
<script type="text/javascript">
	
</script>
<%
	String role = session.getAttribute("role").toString();
%>




</head>
<div id="side-nav">NAVIGATION</div>
<ul class="out-ul">
	<%
		if (role.equals("Admin") || role.equals("SubAdmin")
				|| role.equals("police") || role.equals("Magistrate")) {
	%>
	<li id="out-li1" class="out-li">Complain</li>
	<li class="in-li1 in-li"><a href="./includes/search.jsp"
		class="dynamicLoad">Search Criminals</a>
	</li>
	<li class="in-li1 in-li"><a href="./includes/allcomplain.jsp"
		class="dynamicLoad">All Complains - All Dept.</a>
	</li>
	<li class="in-li1 in-li"><a href="./includes/closedcomplain.jsp"
		class="dynamicLoad">Closed Complains</a>
	</li>
	<%
		if (role.equals("SubAdmin") || role.equals("Admin")) {
	%>
	<li class="in-li1 in-li"><a href="./includes/newcomplaint.jsp"
		class="dynamicLoad">New Complaints</a>
	</li>
	<%
		}
		if (role.equals("police") || role.equals("Admin")) {
	%>
	<li class="in-li1 in-li"><a href="./includes/pendingcomplaint.jsp"
		class="dynamicLoad">Pending Complains</a>
	</li>


	<li id="out-li2" class="out-li">FIR</li>
	<li class="in-li2 in-li"><a href="./includes/pendingfir.jsp"
		class="dynamicLoad">Pending FIRs</a>
	</li>
	</li>
	<%
		}
	%>
	<%
		if (role.equals("Magistrate") || role.equals("Admin")) {
	%>
	<li id="out-li3" class="out-li">Magistrate</li>
	<li class="in-li3 in-li"><a href="./includes/chargesheet.jsp"
		class="dynamicLoad">File Chargesheet</a>
	</li>
	<%
		}
		if (role.equals("Admin")) {
	%>
	<li id="out-li5" class="out-li">VPS</li>
	<li class="in-li5 in-li"><a href="./includes/createvps.jsp"
		class="dynamicLoad">Create VPS</a>
	</li>
	<li class="in-li5 in-li"><a href="./includes/showvps.jsp"
		class="dynamicLoad">Show VPS</a>
	</li>
	<li class="in-li5 in-li"><a href="./includes/createpolice.jsp"
		class="dynamicLoad">Create Police Officer</a>
	</li>

	<%
		}
		if (role.equals("Admin") || role.equals("SubAdmin")
					|| role.equals("police") || role.equals("Magistrate")) {
	%>
	<li id="out-li4" class="out-li">User Profile</li>
	<li class="in-li4 in-li"><a href="./includes/profile.jsp"
		class="dynamicLoad">View Profile</a>
	</li>
	<li class="in-li4 in-li"><a href="./includes/changepassword.jsp"
		class="dynamicLoad">Change Password</a>
	</li>
	<li class="in-li4 in-li"><a href="./includes/logout.jsp">Log
			Out</a>
	</li>
	<%
		}
	%>


	<%
		}
	%>
</ul>