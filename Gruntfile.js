module.exports = function(grunt) {
	grunt.initConfig({ 
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			options: {
				banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
		    },
		    my_target: {
		    	files: {
		    		'WebContent/resources/javascript/site.min.js':['omega_sdk/web/javascript/site.js']
		    	}
		    }
		},
		copy: {
			main: {
				files: [
					{ 
						expand: true, 
						src: ['omega_sdk/modules/javascript/target/scala-2.11/*'], 
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
