(function($) {
	$("#" + disable + " > a").attr('href', 'javascript:void');
	$("#" + disable).addClass("disabled");
	
	/*$('.book-show').on('click', function(e) {
		e.preventDefault();
		bootbox.alert('Show: ' + $(this).attr('href'));
	});*/
	
	/*$('.book-edit').on('click', function(e) {
		e.preventDefault();
		bootbox.alert('Edit: ' + $(this).attr('href'));
	});*/
	
	$('.book-delete').on('click', function(e) {
		e.preventDefault();
		bootbox.alert('Delete: ' + $(this).attr('href'));
	});
})(jQuery);
