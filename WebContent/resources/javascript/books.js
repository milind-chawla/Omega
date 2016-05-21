(function($) {
	$("#" + disable + " > a").attr('href', 'javascript:void');
	$("#" + disable).addClass("disabled");
})(jQuery);
