/*validation.js*/

$(document).ready(
		function() {
			// global vars
			var complainForm = $("#complainForm");
			var complainSubject = $("#complainSubject");
			var complainSubjectInfo = $("#complainSubjectInfo");
			var complainDesc = $("#complainDesc");
			var complainDescInfo = $("#complainDescInfo");

			// On blur
			complainSubject.blur(validateSubject);
			complainDesc.blur(validateComplainDescription);

			// On key press
			complainSubject.keyup(validateSubject);
			complainDesc.keyup(validateComplainDescription);

			// On Submitting
			complainForm.submit(function(e) {
				e.preventDefault(); // prevent the browser from
				// following the link
				e.stopPropagation();
				if (validateComplainDescription() & validateSubject()) {
					alert("hello");
					var data_content = complainForm.serialize();
					alert(data_content);
					$.ajax({
						url : "./site/submit/submit_complain.jsp",
						cache : false,
						data : data_content,
						success : function(data) {
							alert("success" + data);
							window.location = "./?pgid=user";
						},
						error : function(e) {
							alert("error");
						}

					});
					return true;
				} else
					return false;
			});

			// validation functions

			function validateSubject() {
				// if it's NOT valid
				if (complainSubject.val().length < 4) {
					complainSubject.addClass("error");
					complainSubjectInfo
							.text("We want names with more than 3 letters!");
					complainSubjectInfo.addClass("error");
					return false;
				}
				// if it's valid
				else {
					complainSubject.removeClass("error");
					complainSubjectInfo.text("");
					complainSubjectInfo.removeClass("error");
					return true;
				}
			}

			function validateComplainDescription() {
				// if it's NOT valid
				if (complainDesc.val().length < 4) {
					complainDesc.addClass("error");
					complainDescInfo
							.text("We want names with more than 3 letters!");
					complainDescInfo.addClass("error");
					return false;
				}
				// if it's valid
				else {
					complainDesc.removeClass("error");
					complainDescInfo.text("");
					complainDescInfo.removeClass("error");
					return true;

				}
			}

		});