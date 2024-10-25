<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-warning">
		<div class="container-fluid">
			<a class="navbar-brand d-flex align-items-center" href="#"> <img
				src="${pageContext.request.contextPath}/assets/imgs/logo-phone.jpg"
				alt="Logo" width="50" class="img-fluid"> <span class="ms-2">MobileMgr</span>
			</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link nav-link-header"
						href="${pageContext.request.contextPath}/listPhone">Phone List</a></li>
					<li class="nav-item"><a class="nav-link nav-link-header"
						href="${pageContext.request.contextPath}/AddData">Add Data</a></li>
					<li class="nav-item"><a class="nav-link nav-link-header"
						href="${pageContext.request.contextPath}/managerForm">Manage
							Phones</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>