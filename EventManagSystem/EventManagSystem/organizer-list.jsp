<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Organizer Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Organizer
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Organizers</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		

		<div class="container">
			<h3 class="text-center">List of Organizers</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Organizer</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Venue</th>
						<th>Price</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="user" items="${listOrganizer}">

						<tr>
							<td><c:out value="${organizer.id}" /></td>
							<td><c:out value="${organizer.name}" /></td>
							<td><c:out value="${organizer.email}" /></td>
							<td><c:out value="${organizer.venue}" /></td>
							<td><c:out value="${organizer.price}" /></td>
							<td><a href="edit?id=<c:out value='${organizer.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${organizer.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
				
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
