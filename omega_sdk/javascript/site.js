(function($) {
	console.log('jQuery is installed.');
	
	var i = 0;
	var j = 1;
	
	for(i = 0; i < 10; i++) {
		j *= 2;
	}
	
	console.log("j = " + j);
})(jQuery);
