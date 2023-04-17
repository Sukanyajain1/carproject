<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Project</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    	<h1>Add a New Car to the Family!</h1>
    	<a href = "/logout" class = "btn btn-danger mt-2 float-end">Logout</a>

		<form:form action = "/create" method = "post" modelAttribute = "car">
			<form:hidden path = "user" value = "${userId}" />
			<div class = "form-group">
				<label>Color: </label>
				<form:input path="color" class = "form-control"/>
				<form:errors path="color" class = "text-danger"/>
			</div>
			<div class = "form-group">
				<label>Number Of Seats: </label>
				<form:input path="numOfSeats" class = "form-control"/>
				<form:errors path="numOfSeats" class = "text-danger"/>
			</div>
			<input type = "submit" value = "Add New Car" class = "btn btn-success mt-2" />
		</form:form>
		
    </div> <!-- End of Container -->
</body>
</html>