<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
<div class="card-header my-3">All Products</div>
<div class=row>
<div class="col-md-3">
<div class="card-w-100" style="width: 18rem;">
  <img src="..." class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title">Card title</h5>
    <h6 class="price">Price: </h6>
    <p class="card-text">Explanation</p>
    <div class="mt-3 d-flex justif-content-between">
    <a href="#" class="btn btn-primary">add to cart</a>
    <a href="#" class="btn btn-primary">buy now</a>
    </div>
    
    
  </div>
</div>
</div>
</div>
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>