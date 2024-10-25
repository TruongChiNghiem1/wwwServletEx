<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý điện thoại</title>
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
		<div class="container mt-4">
			<div class="card p-3">
				<h2 class="mb-3">Quản lí theo nhà cung cấp</h2>
				<div class="input-group mb-3">
					<input type="text" class="form-control"
						placeholder="Tìm nhà cung cấp" title="Tìm nhà cung cấp"
						id="searchInput"> <select class="form-select"
						id="searchSelect"
						data-context-path="${pageContext.request.contextPath}">
						<option value="all" selected>Tất cả</option>
						<option value="name">Tên</option>
						<option value="address">Địa chỉ</option>
						<option value="phone">Số điện thoại</option>
					</select>
				</div>
			</div>
		</div>
		<c:if test="${message != ''}">
			<div class="d-flex mb-2 mt-2 align-content-center"
				id="error_container">
				<p class="text-danger">${message}</p>
				<button onClick="messageOk()" class="btn btn-primary">ok</button>
			</div>
		</c:if>
		<div id="accordion">
			<c:forEach var="supplier" items="${nhaCungCaps}">
				<div class="card">
					<div class="card-header d-flex justify-content-between">
						<a class="btn" data-bs-toggle="collapse"
							href="#collapse_${supplier.maNCC}"> ${supplier.tenNCC}</a>
						<button type="button" class="btn btn-dark text-white"
							data-bs-toggle="modal" data-bs-target="#supplierDetailModal"
							data-supplier-name="${supplier.tenNCC}"
							data-supplier-address="${supplier.diaChi}"
							data-supplier-phone="${supplier.soDienThoai}"
							data-supplier-id="${supplier.maNCC}"
							data-context-path="${pageContext.request.contextPath}"
							onclick="populateSupplierDetailModal(this)">Chi tiết</button>
					</div>
					<div id="collapse_${supplier.maNCC}" class="collapse "
						data-bs-parent="#accordion">
						<div class="card-body">
							<table class="table">
								<thead>
									<tr>
										<th>Hình ảnh</th>
										<th>Tên điện thoại</th>
										<th>Năm sản xuất</th>
										<th>Thông tin</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="phone" items="${supplier.listDienThoai}">
										<tr>
											<td><img
												src="${pageContext.request.contextPath}/assets/imgs/${phone.hinhAnh}"
												alt="${phone.hinhAnh}" style="width: 100px;"></td>
											<td>${phone.tenDT}</td>
											<td>${phone.namSanXuat}</td>
											<td><button type="button" class="btn btn-primary"
													data-bs-toggle="modal" data-bs-target="#phoneDetailModal"
													data-supplier-id="${supplier.maNCC}"
													data-phone-id="${phone.id}"
													data-phone-name="${phone.tenDT}"
													data-phone-year="${phone.namSanXuat}"
													data-phone-specs="${phone.cauHinh}"
													data-phone-image="${pageContext.request.contextPath}/assets/imgs/${phone.hinhAnh}"
													data-context-path="${pageContext.request.contextPath}"
													onclick="populatePhoneDetailModal(this)">Xem thông
													tin chi tiết</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="modal" id="phoneDetailModal" tabindex="-1"
		aria-labelledby="phoneDetailModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="phoneDetailModalLabel">Thông tin
						điện thoại</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="phoneDetailForm"
						action="${pageContext.request.contextPath}/managerForm"
						method="post" enctype="multipart/form-data">
						<div class="mb-3">
							<label for="phoneImage" class="form-label">Hình ảnh</label> <input
								type="file" class="form-control" id="phoneImage"
								accept=".jpg, .png" style="display: none;"
								onchange="previewImage(event)" name="phoneImage"> <img
								id="imagePreview" src="" alt="Hình ảnh điện thoại"
								style="width: 250px; height: 250px; object-fit: cover">
							<button type="button" class="btn btn-primary mt-2"
								id="updateImageButton"
								onclick="document.getElementById('phoneImage').click()">Cập
								nhật ảnh</button>
						</div>

						<div class="mb-3">
							<input type="hidden" name="supplieid" id="supplieid"> <input
								type="hidden" name="Phoneid" id="idPhone"> <label
								for="phoneName" class="form-label">Tên điện thoại</label> <input
								type="text" name="phoneName" class="form-control" id="phoneName"
								required>
						</div>
						<div class="mb-3">
							<label for="phoneYear" class="form-label">Năm sản xuất</label> <input
								type="text" name="phoneYear" class="form-control" id="phoneYear"
								required>
						</div>
						<div class="mb-3">
							<label for="phoneSpecs" class="form-label">Cấu hình chi
								tiết</label>
							<textarea class="form-control" name="phoneSpecs" id="phoneSpecs"
								rows="3" required></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" id="deletePhone"
						onClick="handleDeletePhone(this)">Delete</button>
					<button type="button" class="btn btn-primary" id="updatePhone"
						onClick="handleUpdatePhone()">Update</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="supplierDetailModal" tabindex="-1"
		aria-labelledby="supplierDetailModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="supplierDetailModalLabel">Thông
						tin nhà cung cấp</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="supplierDetailForm"
						action="${pageContext.request.contextPath}/managerForm"
						method="post">
						<div class="mb-3">
							<input type="hidden" name="idSupplier" id="idSupplier"> <label
								for="supplierName" class="form-label">Tên nhà cung cấp</label> <input
								type="text" class="form-control" id="supplierName"
								name="supplierName">
						</div>
						<div class="mb-3">
							<label for="supplierAdress" class="form-label">Địa chỉ</label> <input
								type="text" class="form-control" id="supplierAdress"
								name="supplierAddress">
						</div>
						<div class="mb-3">
							<label for="supplierPhone" class="form-label">Số điện
								thoại</label> <input type="text" class="form-control" id="supplierPhone"
								name="supplierPhone">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" id="deleteSupplier"
						onClick="handleDeleteSupplier(this)">Delete</button>
					<button type="button" class="btn btn-primary" id="updateSupplier"
						onClick="handleUpdateSupplier()">Update</button>
				</div>
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
