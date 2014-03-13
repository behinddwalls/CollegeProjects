<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#loginForm #send")
								.click(
										function(e) {

											e.preventDefault(); // prevent the browser from following the link
											e.stopPropagation(); // prevent the browser from following the link
											var data_content = $(
													"form#loginForm")
													.serialize();

											alert(data_content);
											$
													.ajax({
														url : "./adm/includes/checklogin.jsp",
														cache : false,
														data : data_content,
														success : function(data) {
															//$("div#customPopup").html(data);
															alert($.trim(data));
															if ($.trim(data) == "SUCCESS1")
																window.location = "http://localhost:8080/TGMC/beta/";
															else if ($
																	.trim(data) == "SUCCESS2")
																window.location = "http://localhost:8080/TGMC/beta/?pgid=user";

														},
														error : function() {

															alert("Login Failed");
														}
													});

										});

					});
</script>


</head>





<div id="left_content">
	<h1>Virtual Police Station</h1>
	<h3>"Serving those who protect"</h3>
	<p> Virtual Police Station is the Web Based Online Portal to deliver next generation police and law enforcement reporting tools, and settingup intelligence platforms that agencies use to take incoming incident reports,lessen employee resources and allow these enforcement agencies to reallocateresources to much needed community areas.
	</p>
</div>
<div id="right_content">
	<h3>Log In :</h3>
	<div id="login">
		<form action="" method="post" id="loginForm">
			<Label>Email id:</Label><br /> <input type="emailid" name="email_id" /><br />
			<label>Password:</label><br /> <input type="password"
				name="password" /> <br /> <label>Log in as:</label><br />
			<div id="radio">
				<input type="radio" name="role" value="Admin" />Admin <input
					type="radio" name="role" value="SubAdmin" />SubAdmin <input
					type="radio" name="role" value="police" />police <input
					type="radio" name="role" value="citizen" checked />Citizen
			</div>
			<br /> <input id="send" type="submit" value="Log in" />
		</form>


	</div>

</div>

<div id="flash" style="margin-left:auto;margin-right:auto;width:100%;clear:both;">
<br/><br/>
<object width="100%" height="300px" data="includes/image/home_flash.swf"></object>
</div>
