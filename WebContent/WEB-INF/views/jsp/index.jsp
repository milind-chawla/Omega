<html>
<body>
<h2>Hello World!</h2>
	<jsp:useBean id="helloBean" class="com.omega.Hello" scope="page"></jsp:useBean>
	
	<h3><jsp:getProperty property="greet" name="helloBean"></jsp:getProperty></h3>
	
	Hello date: ${date}
</body>
</html>
