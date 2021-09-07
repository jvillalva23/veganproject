<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
	<head>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
			rel="stylesheet" 
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
			crossorigin="anonymous">
		<link rel="stylesheet" href="css/main.css" />
		<meta charset="ISO-8859-1">
	<title>Bad Hunter Recipe</title>
</head>
	<body>
	<div class="float-left">
		<h1>${ food.title }</h1>
		<hr>
		<p>Added By: ${ food.foodie.firstName } ${ food.foodie.lastName }</p>	
		<p> Added On: ${ food.createdAt }</p>
		<p> Updated At: ${food.updatedAt }</p>
		<p>Liked By:</p>
			<ol>
				<c:forEach items="${ food.likers }" var="user">
					${ user.firstName } ${ user.lastName }
				</c:forEach>
				<c:choose>
					<c:when test="${ food.likers.contains(user) }"><a href="/foods/unlike/${food.id }">Unlike</a></c:when>
						<c:otherwise><td><a href="/foods/like/${food.id }">Like</a></td></c:otherwise>
				</c:choose>
			</ol>
		<hr>
	</div>
		<div class="float-bottom">
		<c:if test="${food.foodie.id == userId }"> 
			<form:form action="/foods/${ food.id }" method="POST" modelAttribute="food">
				<form:errors path="*"/>
				<h2>Edit ${ food.title }</h2>
					<input type="hidden" name="_method" value="PUT">
					<form:hidden value="${ userId }" path="foodie"/>
					<form:errors path="foodie"/>
				<div class="form-group">
					<form:label path="title">Title</form:label>
					<form:errors path="title"></form:errors>
					<form:input class="form-control" path="title"></form:input>
				</div>
		
				<div class="form-group">
					<form:label path="recipe">Recipe</form:label>
					<form:errors path="recipe"></form:errors>
					<form:input class="form-control" path="recipe"></form:input>
				</div>
			    <button>Update Recipe</button>
			</form:form>
		</c:if>
		</div>
	</body>
</html>