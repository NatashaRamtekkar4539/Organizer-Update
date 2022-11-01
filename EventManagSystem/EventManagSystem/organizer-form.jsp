<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Organizer Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Organizer Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Organizers</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${organizer != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${organizer == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${organizer != null}">
            			Edit Organizer
            		</c:if>
						<c:if test="${organizer == null}">
            			Add New Organizer
            		</c:if>
					</h2>
				</caption>

				<c:if test="${organizer != null}">
					<input type="hidden" name="id" value="<c:out value='${organizer.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Organizer Name</label> <input type="text"
						value="<c:out value='${organizer.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Organizer Email</label> <input type="text"
						value="<c:out value='${organizer.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Organizer Venue</label> <input type="text"
						value="<c:out value='${organizer.venue}' />" class="form-control"
						name="venue">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Organizer Price</label> <input type="text"
						value="<c:out value='${organizer.price}' />" class="form-control"
						name="price">
				</fieldset>
				

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
