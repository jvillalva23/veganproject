<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
			rel="stylesheet" 
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
			crossorigin="anonymous">
		<link rel="stylesheet" href="css/main.css" />
		<meta charset="ISO-8859-1">
	<title>Bad Hunter</title>
</head>
<body>

<div class="container">
			<div><h2>Welcome To Bad Hunter, ${ user.firstName }</h2>
			<a href="/logout">Logout</a>
			</div>
			<div><a href="/foods/cart"><button class="btn btn-dark">Cart</button></a></div>
			<div class="float float-center">
				<h3>Menu</h3>
					<c:forEach items="${ usersFoods }" var="food">
						<div>
							<form method="POST" action="foods/upload" enctype="multipart/form-data">
							<div class="form-group">
								<input type="file" name="pic" class="form-control-file" id="inlineFormInput">
								<button>Upload Pic</button>
								<c:forEach items="${currentUser.pics}" var="pic">
								<img src="${pic.image_url}"/>
								</c:forEach>
							</div>
							</form>
							<td>
							<c:choose>
							<c:when test="${food.foodie.id == userId }">
							<a href="/foods/${ food.id }">${ food.title }</a>
							</c:when>
							<c:otherwise>
							<a href="/foods/${ food.id }" class="h4">${ food.title }</a>
							</c:otherwise>
							</c:choose>
							</td>
							<td><p class="h5">${ food.recipe }</p></td>
							<td><p class="h6">${ food.price }</p></td>
							<c:choose>
								<c:when test="${ food.likers.contains(user) }"><a href="/foods/unlike/${food.id }"><p>Unlike</p></a></c:when>
									<c:otherwise><td><a href="/foods/like/${food.id }"><p>Like</p></a></td></c:otherwise>
							</c:choose>
							<td><a href="/foods/cart"><button class="btn btn-dark p-3 mt-2">Add To Order</button></a></td>
						</div>
					</c:forEach>
			</div>
</body>
</html>