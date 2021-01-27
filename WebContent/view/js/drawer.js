
$(document).ready(function() {
	$('#menu-button').on('click', function() {
		$('#wrapper, #nav').toggleClass('show');
		$('#menu-button').toggleClass('active');
	});
});