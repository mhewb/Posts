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

<jsp:include page="../menu.jsp"></jsp:include>

<div class="container-md p-3">

  <form action="${pageContext.request.contextPath}/create-category" method="post">

    <label for="categoryName" class="form-label">Category Name</label>
    <input type="text" class="form-control" id="categoryName" name="categoryName"
           value="${empty category.name ? '' : category.name }">


    <c:if test="${empty update}">
      <button class="btn btn-primary my-3" type="submit">Create Category</button>
    </c:if>

    <c:if test="${!empty update}">
      <button class="btn btn-primary my-3" type="submit" name="id"
              value=${post.id} formaction="/edit-post?id=${post.id}">Edit</button>

      <a class="btn btn-danger" role="button"
         href="/delete-post?id=${post.id}">
        Delete
      </a>
    </c:if>






  </form>

</div>

<c:if test="${!empty createPostError}">
  <script>alert(${createPostError})</script>
</c:if>

</body>
</html>