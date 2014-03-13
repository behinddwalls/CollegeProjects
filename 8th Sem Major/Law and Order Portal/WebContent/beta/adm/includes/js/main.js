/**
 * 
 */
$( document ).ready( function() {
	$.ajaxSetup({
	    // Disable caching of AJAX responses
	    cache: false
	});
            $( '#load' ).load("./includes/search.jsp");
            
		
});