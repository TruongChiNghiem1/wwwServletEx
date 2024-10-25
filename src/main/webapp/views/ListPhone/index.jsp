<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách điện thoại</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
	<header id="header" class="header"></header>
	<!--  Main-->
	<div class="container mt-3">
		<div class="row">
			<div class="col-4 mx-auto d-flex align-items-center">
				<input type="text" class="form-control"
					placeholder="Tìm tên điện thoại " id="search_name"
					data-context-path="${pageContext.request.contextPath}"
					<c:if test="${param.search != null }">
                    value="${param.search}"
                </c:if>>
				<button class="btn btn-primary ms-2" onClick="handleSearch()">Tìm</button>
			</div>
		</div>
		<div class="row mt-2">
			<div class="col-2">
				<label for="codeSupplier" class="form-label">Nhà cung cấp:</label> <select
					class="form-select" id="codeSupplier" name="codeSupplier" required
					onChange="handleChangeSelection()"
					data-context-path="${pageContext.request.contextPath}">
					<option selected value="all">Tất cả</option>
					<c:forEach var="supplier" items="${nhaCungCaps}">
						<option value="${supplier.maNCC}"
							<c:if test="${param.maNCC != null && param.maNCC != 'all' &&  param.maNCC == supplier.maNCC}">
                    selected
                </c:if>>
							${supplier.tenNCC}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row mt-2">
			<!-- item phone -->
			<c:forEach var="phone" items="${listPhone}">
				<div class="col-3 mt-3">
					<div class="card">
						<div class="img-content">
							<img
								src="${pageContext.request.contextPath}/assets/imgs/${phone.hinhAnh}"
								class="img-fluid img-product" alt="${phone.hinhAnh}">
						</div>
						<div class="card-body">
							<h5 class="card-title" title="${phone.tenDT}">${phone.tenDT}</h5>
							<p class="card-text">Năm sản xuất: ${phone.namSanXuat}</p>
							<button class="btn btn-primary">Chi tiết</button>
						</div>
					</div>
				</div>
			</c:forEach>
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