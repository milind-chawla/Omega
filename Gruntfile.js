module.exports = function(grunt) {
	grunt.initConfig({ 
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			options: {
				banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
		    },
		    my_target: {
		    	files: {
		    		'omega_sdk/modules/javascript/omega.min.js':['omega_sdk/modules/javascript/omega.js']
		    	}
		    }
		}
		,copy: {
			main: {
				files: [
					{ 
						expand: true, 
						src: ['omega_sdk/modules/javascript/*.min.js'], 
						dest: 'WebContent/resources/javascript/',
						filter: 'isFile',
						flatten: true
					}
				]
			}
		}
	});
	
	// grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-copy');
};
