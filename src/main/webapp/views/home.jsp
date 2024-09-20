<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${role}Home</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	text-align: center;
	margin: 0;
	padding: 20px;
	background-color: #f0f0f0;
}

h1 {
	color: #333;
	margin-bottom: 20px;
}

button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	text-align: center;
	text-decoration:   none;
	display: inline-block;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background-color:   #3e8e41;
}
</style>
</head>
<body>
	<form action="/Ltweb/home" method="POST">
		<h1>Welcome to the ${role} Dashboard</h1>
		<button type="submit">Logout</button>
	</form>


</body>
</html>