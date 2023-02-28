<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: black">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Employee Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${employee != null}">
					<form action="update" method="post"></form>
				</c:if>
				<c:if test="${employee == null}">
					<form action="insert" method="post"></form>
				</c:if>

				<h2>
						<c:if test="${employee != null}">
            			Edit User
            		</c:if>
						<c:if test="${employee == null}">
            			Add New Employee
            		</c:if>
					</h2>
				</caption>

				<c:if test="${employee != null}">
					<input type="hidden" name="id" value="<c:out value='${employee.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Employee Name</label> <input type="text"
						value="<c:out value='${employee.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Employee Department</label> <input type="text"
						value="<c:out value='${employee.department}' />" class="form-control"
						name="department">
				</fieldset>

				<fieldset class="form-group">
					<label>Employee Designation</label> <input type="text"
						value="<c:out value='${employee.designation}' />" class="form-control"
						name="designation">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				
			
			</div>
		</div>
	</div>

</body>
</html>