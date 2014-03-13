<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.PoliceController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (session.getAttribute("pid") != null
		&& !session.getAttribute("pid").toString().isEmpty()) {
%>
<head>
<script>
	$(document).ready(function() {
		$('a.custompopup').click(function(e) {
			e.preventDefault(); // prevent the browser from following the link
			e.stopPropagation(); // prevent the browser from following the link
			// $( '#load' ).load( $( this ).attr( 'href' ));

			//var data-content = $(this).attr('data-content');
			//alert(data);

			$("#customPopup").show();
			$("#backShadow").show();
			$.ajax({
				url : $(this).attr('href'),
				cache : false,
				data : $(this).attr('data-content'),
				success : function(data) {
					$("div#customPopup").html(data);
				}
			});

		});

		$("#backShadow img").click(function() {
			$("#customPopup").hide();
			$("#backShadow").hide();
		});

	});
</script>
<%
	int uid = (Integer) session.getAttribute("pid");// (String) session.getAttribute("citizenid");
		ArrayList<Integer> complaint_id = new ArrayList<Integer>();
		ArrayList<Integer> citizen_id = new ArrayList<Integer>();
		ArrayList<String> complaint_content = new ArrayList<String>();
		ArrayList<String> complaint_time = new ArrayList<String>();
		ArrayList<String> complaint_status = new ArrayList<String>();
		ArrayList<String> complaint_subject = new ArrayList<String>();
		int dept_id = (Integer) session.getAttribute("dept_id");
		int vps_id = (Integer) session.getAttribute("vps_id");
		if (uid != 0) {
			PoliceController police = new PoliceController();
			Response resp = police.retrieveComplainChargesheet(dept_id,
					vps_id);// out.println(resp.getEntity());
			try {
				JSONArray jArray = new JSONArray(resp.getEntity()
						.toString());

				for (int i = 0; i < jArray.length(); i++) {

					complaint_time.add(jArray.getJSONObject(i)
							.getString("complaint_time"));
					complaint_subject.add(jArray.getJSONObject(i)
							.getString("complain_subject"));

					complaint_id.add(jArray.getJSONObject(i).getInt(
							"complain_id"));

					complaint_status.add(jArray.getJSONObject(i)
							.getString("complaint_status"));

					citizen_id.add(jArray.getJSONObject(i).getInt(
							"citizen"));

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
%>


</head>
<div class="complainStatus table">
	<table>
		<tr>
			<th>S.No.</th>
			<th>Complain Subject</th>
			<th>Time</th>
			<th>Status</th>
			<th>Check Complain</th>
		</tr>
		<%
			for (int i = 0; i < complaint_id.size(); i++) {
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><%=complaint_subject.get(i)%></td>
			<td><%=complaint_status.get(i)%></td>
			<td><%=complaint_time.get(i)%></td>

			<td><div class="buttonType ">
					<a href="./includes/filechargesheet.jsp"
						data-content="citizenId=<%=citizen_id.get(i)%>&complaintId=<%=complaint_id.get(i)%>&id=id<%=i%>&ref=chargesheet"
						id="id<%=i%>" class="custompopup">File Chargesheet</a>
				</div>
			</td>
		</tr>
		<%
			}
		%>



	</table>




</div>
<%
	} else {
			response.sendRedirect("http://localhost:8080/TGMC/beta");
		}
%>
<%
	} else
		response.sendRedirect("./../");
%>