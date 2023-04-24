<%--
  Created by IntelliJ IDEA.
  User: mhe
  Date: 14/04/2023
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Post Project</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container-md p-3">

  <c:if test="${!empty username}">

    <div class="row">

      <div class="col-6 p-5">

        <form action="${pageContext.request.contextPath}/logout" method="post">
          <button class="btn btn-danger" type="submit">Logout</button>
        </form>

      </div>
    </div>

  </c:if>

  <c:if test="${empty isLogged}">

  <div class="row">

    <div class="col-6 p-5">

      <form class="row g-3" action="${pageContext.request.contextPath}/login" method="post">

        <div class="col-md-6">
          <label for="username" class="form-label">Username</label>
          <input type="text" class="form-control" id="username" name="username" value="admin">
        </div>
        <div class="col-md-6">
          <label for="password" class="form-label">Password</label>
          <input type="password" class="form-control" id="password" name="password" value="admin">
        </div>

        <button class="btn btn-primary" type="submit">Login</button>

      </form>

    </div>


    <div class="col-5 p-5 bg-secondary bg-opacity-10">

    <form class="row g-3" action="${pageContext.request.contextPath}/register" method="post">
      <div class="col-md-6">
        <label for="usernameRegister" class="form-label">Username</label>
        <input type="text" class="form-control" id="usernameRegister" name="username">
      </div>
      <div class="col-md-6">
        <label for="passwordRegister" class="form-label">Password</label>
        <input type="password" class="form-control" id="passwordRegister" name="password">
      </div>
      <div class="col">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email">
      </div>
      <button class="btn btn-secondary" type="submit">Register</button>
    </form>

    </div>


  </div>

  </c:if>

  <c:if test="${!empty loggingError}">
    <script>alert('${loggingError}')</script>
  </c:if>

  <c:if test="${!empty registerError}">
    <script>alert('${registerError}')</script>
  </c:if>

  <c:if test="${registerUser == true}">
    <script>alert('Welcome ${username} ! Do not forget your password: `${password}`')</script>
  </c:if>





</div>

</body>
</html>