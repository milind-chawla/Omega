module.exports = function(grunt) {
	grunt.initConfig({ 
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			options: {
				banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
		    },
		    my_target: {
		    	files: {
		    		'WebContent/resources/javascript/omega.min.js': ['WebContent/resources/javascript/omega.js']
		    	}
		    }
		}
		,copy: {
			main: {
				files: [
					{   expand: true,
						cwd: 'bower_components/jquery/',
						src: ['**'], 
						dest: 'WebContent/resources/jquery/'
					},
					{   expand: true,
						cwd: 'bower_components/bootstrap/',
						src: ['**'], 
						dest: 'WebContent/resources/bootstrap/'
					},
					{   expand: true,
						cwd: 'bower_components/angular/',
						src: ['**'], 
						dest: 'WebContent/resources/angular/'
					}
				]
			}
		}
	});
	
	// grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-copy');
};
