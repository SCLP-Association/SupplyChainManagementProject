<nav class="navbar navbar-expand-lg bg-body-tertiary" style="background-color: #e3f2fd">
<div class="container">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">SCM Project</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
        <%if(auth!=null) {%>
        <li class="nav-item">
          <a class="nav-link" href="orders.jsp">Orders</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout.jsp">Logout</a>
        </li>
        <%}else{ %>
        <li class="nav-item">
          <a class="nav-link" href="register.jsp">Register</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="login.jsp">Login</a>
        </li>
        <%} %>
        <li class="nav-item">
          <a class="nav-link" href="about.jsp">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled">Disabled</a>
        </li>
      </ul>

    </div>
  </div>
  </div>
</nav>