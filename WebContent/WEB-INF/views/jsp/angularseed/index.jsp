<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
  	<meta name='description' content='books index page'>
    <title>AngularSeed</title>
  </head>
  <body>
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<strong>AngularSeed</strong>
			</h3>
	  	</div>
	  	
	  	<div class="panel-body">
	  		<div ng-app>
	  			<div ng-init="salary=0;percentage=0">
	  				Your Salary ? <input type="text" ng-model="salary"> <br>
	  				How much should you invest in gadgets? <input type="text" ng-model="percentage"> % <br>
	  				The amount to be spent in shopping will be: <span>{{salary*percentage*0.01}}</span>
	  			</div>
	  		</div>
	  	</div>
	  	
	  	<content tag="javascripts">
	  		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/angular/angular.js"></script>
	  	</content>
	</div>
  </body>
</html>
