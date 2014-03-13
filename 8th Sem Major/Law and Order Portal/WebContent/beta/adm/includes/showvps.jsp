<%@page import="com.law.order.model.utility.DBUtility"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.law.order.model.VpsArea"%>
<%@page import="com.law.order.controller.PoliceController"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.VpsController"%>
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


</head>
<%
	VpsController vpsc = new VpsController();
		PoliceController police = new PoliceController();
		Response res = vpsc.retrieveVpsAll();

		JSONArray jArray = new JSONArray(res.getEntity().toString());
		//out.println(jArray.get(0));
%>





<div class="complainStatus table">
	<table>
		<tr>
			<th>S.No.</th>
			<th>VPS NAME</th>
			<th>Sub-Admin Name</th>
			<th>Areas</th>
			<th>Update VPS</th>
		</tr>
		<%
			for (int i = 0; i < jArray.length(); i++) {
					JSONObject json = new JSONObject(jArray.get(i).toString());
					Response policeDetail = police.policeofficeretrieve(
							json.getInt("vps_admin_id"), "", "showpolice");
					JSONObject jsonPolice = new JSONObject(policeDetail
							.getEntity().toString());

					List<String> areaList = new VpsArea()
							.getAreaNameForVPS(json.getInt("vps_id"));
					List<Integer> areaidList = new VpsArea()
							.getAreaIdForVPS(json.getInt("vps_id"));
					List<Integer> deptIdList = new DBUtility()
							.getDeptForVPS(json.getInt("vps_id"));

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("vps_id", json.getInt("vps_id"));
					map.put("vps_name", json.get("vps_name"));
					map.put("vps_admin_id", json.getInt("vps_admin_id"));
					map.put("pincode_list",
							new DBUtility().getPincodeAreaWise(areaidList));
					map.put("dept_id_list", deptIdList);
					map.put("officer_id_list",
							vpsc.getOfficerIdList(json.getInt("vps_id"))
									.getEntity().toString());

					//out.println(map.get("pincode_list").toString());
		%>
		<tr>
			<td><%=json.get("vps_id")%></td>
			<td><%=json.get("vps_name")%></td>
			<td><%=jsonPolice.get("officer_name")%></td>
			<td>
				<%
					for (String area : areaList) {
								out.println(area + ",");
							}
				%>
			</td>
			<td><div class="buttonType ">
					<a
						href='./includes/updatevps.jsp?vps_id=<%=json.getInt("vps_id")%>&vps_name=<%=json.get("vps_name")%>&vps_admin_id=<%=json.getInt("vps_admin_id")%>&pincode_list=<%=new DBUtility().getPincodeAreaWise(areaidList)%>&dept_id_list=<%=deptIdList%>&officer_id_list=<%=vpsc.getOfficerIdList(json.getInt("vps_id"))
							.getEntity().toString()%>'
						data-content="ref=showvps&id=id<%=i%>" id="id<%=i%>"
						class="custompopup">Update VPS</a>
				</div></td>
		</tr>
		<%
			}
		%>



	</table>




</div>
<%
	} else
		response.sendRedirect("./../");
%>