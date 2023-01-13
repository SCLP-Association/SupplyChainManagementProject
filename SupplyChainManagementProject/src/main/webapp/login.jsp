<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp" %>
</head>
<body>
<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card header text-center">User Login</div><br>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email Adress</label><br>
						<input class="form-control" type="email" name="login-email" placeholder="enter your email" required>
					</div>
					<br>
					<div class="form-group">
						<label>Password</label>
						<input class="form-control" type="password" name="login-password" placeholder="********" required>
					</div>
					<br>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
					
				</form>
			</div>
		</div>
	</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>