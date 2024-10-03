<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>
<body>
	<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
		
		<input type="text" id="categoryid" name="categoryid" value="${cate.categoryid}" hidden> 
		<label for="categoryname">Category name:</label><br> 
		<input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br> 
			
		<label for="images">Image:</label><br> 
		<input type="text" id="images" name="images""><br> 
		<c:if test="${cate.images.substring(0,5) != 'https'}">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>

			<c:if test="${cate.images.substring(0,5) == 'https'}">
				<c:url value="${cate.images}" var="imgUrl"></c:url>
			</c:if>
		<img height="150" width="200" src="${imgUrl}" />
		<input type="file" id="images_up" name="images_up" value ="${cate.images}"> <br>
  			
			
			
		<label for="status">Status:</label><br> 
		<input type="text" id="status" name="status" value="${cate.status}"><br> 
		<br>
		
		<input type= "submit" value= "Update">
	</form>
</body>