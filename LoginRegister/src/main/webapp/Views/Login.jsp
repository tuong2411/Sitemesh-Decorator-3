<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>
</head>
<body>
	<center>
		<h2>Welcome</h2>
		<form action="${pageContext.request.contextPath}/Login" method="post">
			<!-- Cập nhật đường dẫn -->
			<c:if test="${not empty alert}">
				<!-- Sửa điều kiện hiển thị thông báo -->
				<h3 class="alert alert-danger">${alert}</h3>
				<!-- Sửa class alert -->
			</c:if>

			<div class="container">
				<label for="uname"><b>Tên người dùng</b></label> <input type="text"
					placeholder="Nhập tên người dùng" name="uname" required> <label
					for="psw"><b>Mật khẩu</b></label> <input type="password"
					placeholder="Nhập mật khẩu" name="psw" required>

				<button type="submit">Đăng Nhập</button>
				<label> <input type="checkbox" name="remember"> Nhớ
					tôi
				</label>
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button" class="cancelbtn">Hủy</button>
				<span class="psw">Quên <a href="/Week2/forget">mật khẩu?</a></span>
			</div>
		</form>
	</center>
</body>
</html>
