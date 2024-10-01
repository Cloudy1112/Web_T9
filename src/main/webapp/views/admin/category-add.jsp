<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>
<body>
	<form action="${pageContext.request.contextPath}/admin/category/insert" method="post">
		<label for="fname">Category name:</label><br> 
		<input type="text"
			id="categoryname" name="categoryname"><br> 
			
		<label for="lname">Link image:</label><br> 
		<input type="text"
			id="images" name="images"><br> 
			
		<label for="lname">Status:</label><br> 
		<label for="css">Hoat dong:</label>
		<input type="radio"
			id="ston" name="status" value = 1><br> 
		<label for="javascript">Khoa:</label> 
		<input type="radio"
			id="stoff" name="status" value = 0> <br>
		
		<input type= "submit" value= "Insert">
	</form>
</body>