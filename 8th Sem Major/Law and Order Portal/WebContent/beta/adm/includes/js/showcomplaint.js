$(document).ready(
		function() {
			$("#dept").bind(
					'change',
					function() {
						// alert($(this).find('option:selected').val());
						var selected = $(this).find('option:selected').val();
						var option = $('<option/>');
						var selector = $('#subDept');
						if (selected === "1") {
							alert("Select 1");

							$(selector).find('option').remove().end().append(
									'<option value="foo">foo</option>').append(
									'<option value="bar">bar</option>')

						} else if (selected === "2") {
							alert("Select 2");

							$(selector).find('option').remove().end().append(
									'<option value="foo">foo</option>').append(
									'<option value="bar">bar</option>')

						} else if (selected === "3") {

							alert("Select 3");

							$(selector).find('option').remove().end().append(
									'<option value="foo">foo</option>').append(
									'<option value="bar">bar</option>')

						} else {

							alert("Select Other");
						}

					});

			$('#send').click(
					function(e) {

						alert("A");
						e.preventDefault(); // prevent the browser from
						// following the link
						e.stopPropagation(); // prevent the browser from
						// following the link
						var data_content = $("form#complainForm").serialize();
						var id = $("#send").attr("data-content");

						alert(id);
						alert(data_content);
						$.ajax({
							url : $(this).attr('href'),
							cache : false,
							data : data_content,
							success : function(data) {
								alert("success");
								$("#" + id).html("DONE !!! ").css("color",
										"rgb(51, 255, 0)");
								$("#customPopup").hide();
								$("#backShadow").hide();

							}
						});
						// alert(data_content);

					});

			$('#cancel').click(function(e) {

				alert("A");

				e.preventDefault(); // prevent the browser from following the
				// link
				e.stopPropagation(); // prevent the browser from following
				var data_content = $("form#complainForm").serialize();
				var id = $("#cancel").attr("data-content");

				alert(id);
				alert(data_content);
				$.ajax({
					url : $(this).attr('href'),
					cache : false,
					data : data_content,
					success : function(data) { //
						alert("success");
						$("#" + id).html("REJECTD ").css("color", "RED");
						$("#customPopup").hide();
						$("#backShadow").hide();
					}
				});

				// alert(data_content);
			});
            var ref = '<%=request.getParameter("ref")%>';
			if (ref == "new") {
				alert(ref);
				$("#send").val("Assign Dept & Pfficer");

			} else {
				$("#complainForm input").attr('disabled', 'disabled');
				$("#complainForm select").attr('disabled', 'disabled');
				$("#complainForm textarea").attr('disabled', 'disabled');
				$("#complainForm #send").removeAttr('disabled', 'disabled');
				$("#send").val("Create FIR");

			}
		});
