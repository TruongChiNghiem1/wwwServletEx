<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm dữ liệu</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

</head>
<body>
	<header id="header" class="header"></header>
	<!-- Main -->
	<div class="container mt-3">
		<%
		String action = request.getParameter("action");
		%>
		<h2>Thêm dữ liệu</h2>
		<br>
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="nav-item"><a
				class="nav-link <%="formDienThoai".equals(action) || action == null ? "active" : ""%>"
				data-bs-toggle="tab" href="#phone">Thêm điện thoại</a></li>
			<li class="nav-item"><a
				class="nav-link <%="formNhacc".equals(action) ? "active" : ""%>"
				data-bs-toggle="tab" href="#supplier">Thêm Nhà Cung Cấp</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<!-- Form add phone -->
			<div id="phone"
				class="container tab-pane <%="formDienThoai".equals(action) || action == null ? "active" : "fade"%>">
				<br>
				<form action="${pageContext.request.contextPath}/AddData"
					method="POST" enctype="multipart/form-data">
					<input type="hidden" name="action" value="formDienThoai">
					<div class="mb-3">
						<label for="phoneName" class="form-label">Tên điện thoại:</label>
						<input type="text" class="form-control" id="phoneName"
							name="phoneName" placeholder="Nhập tên điện thoại" required
							value="<%=request.getParameter("phoneName") != null ? request.getParameter("phoneName") : ""%>">
					</div>
					<div class="mb-3">
						<label for="phoneYear" class="form-label">Năm sản xuất:</label> <input
							type="number" class="form-control" id="phoneYear"
							name="phoneYear" placeholder="Nhập năm sản xuất" required
							value="<%=request.getParameter("phoneYear") != null ? request.getParameter("phoneYear") : ""%>">
					</div>
					<div class="mb-3">
						<label for="configPhone" class="form-label">Cấu hình:</label>
						<textarea class="form-control" id="configPhone" name="configPhone"
							placeholder="Nhập cấu hình điện thoại" required><%=request.getParameter("configPhone") != null ? request.getParameter("configPhone") : ""%></textarea>
					</div>
					<div class="mb-3">
						<label for="codeSupplier" class="form-label">Nhà cung cấp:</label>
						<select class="form-select" id="codeSupplier" name="codeSupplier"
							required>
							<option selected disabled>Chọn nhà cung cấp</option>
							<c:forEach var="supplier" items="${nhaCungCaps}">
								<option value="${supplier.maNCC}"
									<c:if test="${param.codeSupplier != null && param.codeSupplier == supplier.maNCC}">
                    selected
                </c:if>>
									${supplier.tenNCC}</option>
							</c:forEach>
						</select>
					</div>

					<div class="mb-3">
						<label for="phoneImage" class="form-label">Ảnh điện thoại:</label>
						<input type="file" class="form-control" id="phoneImage"
							name="phoneImage" required>
					</div>
					<!-- ********  -->
					<c:if test="${statusDt}">
						<div class="text-success mt-1">${message}</div>
					</c:if>
					<c:if test="${!statusDt}">
						<div class="text-danger mt-1">${message}</div>
					</c:if>
					<button type="submit" class="btn btn-primary">Thêm điện
						thoại</button>
				</form>

			</div>

			<!-- Form add suplier -->
			<div id="supplier"
				class="container tab-pane <%="formNhacc".equals(action) ? "active" : "fade"%>">
				<br>
				<form action="${pageContext.request.contextPath}/AddData"
					method="POST" autocomplete="off">
					<input type="hidden" name="action" value="formNhacc">
					<div class="mb-3">
						<label for="supplierName" class="form-label">Tên nhà cung
							cấp:</label> <input type="text" class="form-control" id="supplierName"
							name="supplierName" placeholder="Nhập tên nhà cung cấp" required
							value="<%=request.getParameter("supplierName") != null ? request.getParameter("supplierName") : ""%>">

					</div>
					<div class="mb-3">
						<label for="supplierAddress" class="form-label">Địa chỉ:</label> <input
							type="text" class="form-control" id="supplierAddress"
							name="supplierAddress" placeholder="Nhập địa chỉ nhà cung cấp"
							required
							value="<%=request.getParameter("supplierAddress") != null ? request.getParameter("supplierAddress") : ""%>">
					</div>
					<div class="mb-3">
						<label for="supplierContact" class="form-label">Thông tin
							liên hệ:</label> <input type="text" class="form-control"
							id="supplierContact" name="supplierContact"
							placeholder="Nhập số điện thoại" required
							value="<%=request.getParameter("supplierContact") != null ? request.getParameter("supplierContact") : ""%>">
						<!-- ********  -->
						<c:if test="${statusNcc}">
							<div class="text-success mt-1">${message}</div>
						</c:if>
						<c:if test="${!statusNcc}">
							<div class="text-danger mt-1">${message}</div>
						</c:if>
					</div>
					<button type="submit" class="btn btn-primary">Thêm nhà
						cung cấp</button>
				</form>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
	<script>
		load("#header",
				`${pageContext.request.contextPath}/views/templates/header.jsp`);
	</script>
	<script src="${pageContext.request.contextPath}/assets/js/active.js"></script>

</body>
</html>
