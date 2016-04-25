module.exports = function(grunt) {
	grunt.initConfig({ 
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			options: {
				banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
		    },
		    my_target: {
		    	files: {
		    		'omega_sdk/modules/javascript/omega.min.js': ['omega_sdk/modules/javascript/omega.js']
		    	}
		    }
		}
		,copy: {
			main: {
				files: [
					{ 
						expand: true,
						cwd: 'omega_sdk/modules/javascript/',
						src: ['*.min.js'], 
						dest: 'WebContent/resources/javascript/'
					},
					{   expand: true,
						cwd: 'bower_components/jquery/',
						src: ['**'], 
						dest: 'WebContent/resources/jquery/'
					},
					{   expand: true,
						cwd: 'bower_components/bootstrap/',
						src: ['**'], 
						dest: 'WebContent/resources/bootstrap/'
					}
				]
			}
		}
	});
	
	// grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-copy');
};
