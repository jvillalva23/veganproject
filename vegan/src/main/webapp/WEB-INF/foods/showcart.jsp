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

<div>
<h2>Your Shopping Cart</h2>
<br>
</div>

<div class="row m-1">
	<div class="column-sm-8">
		<block each="item, status : ${cartItems}">
			<div class="row border rounded" with="product = ${item.food}">
				<div class="column-1">
					<div>[[${status.count}]]</div>
					<div> <a class="fas fa-trash icon-dark" href="/removeItem">Remove</a></div>
				</div>
				<div class="column-3">
					<img src="${pic.image_url}"/>
				</div>
				<div class="column-6">
					<div>
					<a href="${food.title}" target="blank">
						<b>${food.title}</b>
					</a>
					</div>
					<div><input type="number" value="${cartItem.quantity}" class="form-control"/></div>
					<div>
						<span>X</span>
						<span>${food.price}</span>
					</div>
					<div class="mt-2">
						<span>=</span><span class="h4">[subtotal]</span>
					</div>
					<div class="mt-2">
						<button class="btn btn-danger p-3 mt-2">Checkout</button>
					</div>
				</div>
			</div>
		<div class="row m-1">%nsbp</div>
		</block>
	</div>
	
	<div class="column-sm-4">
		<div>
			<span class="h5">Estimated Total</span>
		</div>
		<div>
			<span class="h4">[Total Amount]</span>
		</div>
	
	</div>
</div>

</body>
</html>