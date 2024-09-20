<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* Bordered form */
form {
	border: 3px solid #f1f1f1;
}

/* Full-width inputs */
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

/* Set a style for all buttons */
button {
	background-color: #04AA6D;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	justify-content: center;
}

/* Add a hover effect for buttons */
button:hover {
	opacity: 0.8;
}

/* Avatar image */
img.avatar {
	width: 40%;
	border-radius: 50%;
}

/* The "Forgot password" text */
span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
}
</style>
</head>
<body>
	<form action="/Ltweb/forgot" method="post">
		<c:if test="${alert !=null}">
			<h3 class="alert alert danger">${alert} </h3>
		</c:if>
		
		<div class="container">
			<h1>Reset password</h1>
			<p>Please fill in this form to reset your password.</p>
			<hr>
			<label for="uname"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="username" required="required"> <label
				for="psw"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="password" required="required">
			<hr>

		</div>
		<button type="submit">Reset</button>
		<button type="button" class="loginbtn" onclick="location.href='/Ltweb/login';">Login</button>
	</form>
</body>
</html>