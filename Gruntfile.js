module.exports = function(grunt) {
	grunt.initConfig({ 
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			options: {
				banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
		    },
		    my_target: {
		    	files: {
		    		'WebContent/resources/javascript/site.min.js':['omega_sdk/javascript/site.js']
		    	}
		    }
		}
	});
	
	// grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-uglify');
};
