(function($) {
	if(ppage < 1) {
		$("#prev>a").attr('href', 'javascript:void');
		$("#prev").addClass("disabled");
	}
	
	if(xsz < 5) {
		$("#next>a").attr('href', 'javascript:void');
		$("#next").addClass("disabled");
	}
})(jQuery);
