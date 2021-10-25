<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Table 07</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%--    <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>--%>

<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">--%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body style="font-family: 'Century Gothic'">
<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center mb-5">
                <h2 class="heading-section" style="font-family: 'Century Gothic'">Human Being collection</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form method="get">
                    <input name="pageSize" type="number" min=1 value="${pageSize}"/>
                    <input name="pageNumber" type="number" min=1 value="${pageNumber}"/>
                    <input type="submit" value="Pagination" class="btn-outline-dark">
                </form>
                <div class="table-wrap">
                    <table class="table table- bordered table-dark table-hover sortable">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>x</th>
                            <th>y</th>
                            <th>creation date</th>
                            <th>real hero</th>
                            <th>has tooth pick</th>
                            <th>impact speed</th>
                            <th>soundtrack name</th>
                            <th>minutes of waiting</th>
                            <th>weapon type</th>
                            <th>car</th>
                            <th>actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="human_being" items="${human_beings}">
                            <tr>
                                <td><c:out value="${human_being.id}"/></td>
                                <td><c:out value="${human_being.name}"/></td>
                                <td><c:out value="${human_being.coordinates.x}"/></td>
                                <td><c:out value="${human_being.coordinates.y}"/></td>
                                <td><c:out value="${human_being.creationDate}"/></td>
                                <td><c:out value="${human_being.realHero}"/></td>
                                <td><c:out value="${human_being.hasToothpick}"/></td>
                                <td><c:out value="${human_being.impactSpeed}"/></td>
                                <td><c:out value="${human_being.soundtrackName}"/></td>
                                <td><c:out value="${human_being.minutesOfWaiting}"/></td>
                                <td><c:out value="${human_being.weaponType}"/></td>
                                <td><c:out value="${human_being.car.name}"/></td>
                                <td><a href="<%=request.getContextPath()%>/human_being/edit?id=<c:out value='${human_being.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="<%=request.getContextPath()%>/human_being/delete?id=<c:out value='${human_being.id}' />">Delete</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <a href="<%=request.getContextPath()%>/human_being/new">Add New Human Being</a>
    </div>
</section>
<script src="js/sort.js"></script>
</body>
</html>