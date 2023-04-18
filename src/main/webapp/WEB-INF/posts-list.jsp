<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: mhe
  Date: 12/04/2023
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Posts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container-md my-5 p-3">

    <h1 class="p-5">nb of posts: ${ posts.size() }</h1>

    ${!empty isLog ? '<p>You need to be logged to see the posts</p>' : ''}

    <c:if test="${empty isLog}">

    <div class="row row-cols-1 row-cols-sm-3 row-cols-lg-4">

            <c:forEach var="p" items="${posts}">

                <div class="col my-3">
                    <div class="card h-100">


                        <c:if test="${empty p.imgUrl}">
                            <img src="..." class="card-img-top" alt="...">
                        </c:if>

                        <c:if test="${!empty p.imgUrl}">
                            <img src="${p.imgUrl}" class="card-img-top" alt="...">
                        </c:if>


                        <div class="card-body">

                            <h5 class="card-title"> ${p.title} </h5>

                            <h6 class="card-subtitle mb-2 text-muted fw-lighter">
                                from: ${p.author}
                            </h6>

                            <h6 class="card-subtitle mb-2 text-muted fw-lighter">
                                <fmt:parseDate value="${p.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="postTime" type="both"/>
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${postTime}"/> @
                                <fmt:formatDate pattern="HH:mm" value="${postTime}"/>
                            </h6>

                            <p class="card-text"> ${p.content} </p>

                        </div>
                    </div>
                </div>

            </c:forEach>
    </div>

    </c:if>
</div>

</body>
</html>

