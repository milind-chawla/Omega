module.exports = function(grunt) {
	grunt.initConfig({ 
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			options: {
				banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
		    },
		    omega: {
		    	files: {
		    		'WebContent/resources/javascript/omega.min.js': ['WebContent/resources/javascript/omega.js']
		    	}
		    }
		}
		,copy: {
			jquery: {
				files: [
					{   expand: true,
						cwd: 'bower_components/jquery/',
						src: ['**'], 
						dest: 'WebContent/resources/jquery/'
					}
				]
			},
			bootstrap: {
				files: [
					{   expand: true,
						cwd: 'bower_components/bootstrap/',
						src: ['**'], 
						dest: 'WebContent/resources/bootstrap/'
					}
				]
			},
			angular: {
				files: [
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
