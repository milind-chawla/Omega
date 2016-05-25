(function($) {
	$("#" + disable + " > a").attr('href', 'javascript:void');
	$("#" + disable).addClass("disabled");
	
	$('.book-delete').on('click', function(e) {
		bootbox.alert('Delete: ' + $(this).attr('href'));
	});
})(jQuery);
