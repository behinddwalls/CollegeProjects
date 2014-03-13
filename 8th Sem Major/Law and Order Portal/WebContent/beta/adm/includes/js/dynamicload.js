/**
 * 
 */

$(document).ready(function() {

	
	
	$('a.dynamicLoad').click( function(e) {
		e.preventDefault(); // prevent the browser from following the link
		e.stopPropagation(); // prevent the browser from following the link
		// $( '#load' ).load( $( this ).attr( 'href' ));

		$.ajax({
			url : $(this).attr('href'),
			cache : false,
			success : function(data) {
				$("div#load").html(data);
			}
		});

	});
	

	

});