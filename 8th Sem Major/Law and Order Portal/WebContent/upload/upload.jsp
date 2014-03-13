<html>
<head>

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="jquery.form.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#photoimg').bind('change', function() {
			$("#preview").html('');
			$("#preview").html('<p><img src="loader.gif" alt="Uploading...."/></p>');
			$("#imageform").ajaxForm({
				target : '#preview'
			}).submit();
		});
	});
</script>

</head>
<body>
	<h1>JAX-RS Upload Form</h1>

	<form action="http://localhost:8080/TGMC/v1/upload/file" method="post"
		enctype="multipart/form-data" id="imageform">

		<p>
			Select a file : <input type="file" name="uploadedFile" size="50"
				id="photoimg" />
		</p>
		<!--  <p>
       The Next file : <input type="file" name="uploadedFile2" size="50" />
       </p> -->

		<!-- <input type="submit" value="Upload It" /> -->
	</form>
	<div id='preview'>Here</div>

</body>
</html>