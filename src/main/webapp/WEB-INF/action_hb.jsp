<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Human Being</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body style="font-family: 'Century Gothic'">
<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center mb-5">
                <h2 class="heading-section">
                    <c:if test="${human_being != null}">
                        Edit User
                    </c:if>
                    <c:if test="${human_being == null}">
                        Add New User
                    </c:if></h2>
            </div>
        </div>
        <div style="align-content: center" align="center">
        <c:if test="${human_being != null}">
        <form action="update" method="post" style="align-content: center">
            </c:if>

            <c:if test="${human_being == null}">
            <form action="insert" method="post">
                </c:if>


                <c:if test="${human_being != null}">
                    <input type="hidden" name="id" value="<c:out value='${human_being.id}' />"/>
                </c:if>

<%--                <label>Name</label>--%>
                    <input type="text" name="name" value="<c:out value='${human_being.name}' />"
                           placeholder="Name" required>
                    <br>

<%--                <label>X</label>--%>
                <input type="number" name="x" placeholder="X-axis" style="margin-top: 15px;" required
                       value="<c:out value='${human_being.coordinates.x}' />">
                    <br>

<%--                <label>Y</label>--%>
                <input type="number" name="y" placeholder="Y-axis" style="margin-top: 15px;" required
                       value="<c:out value='${human_being.coordinates.y}' />">
                    <br>
                <label>Real hero</label>
                <input type="checkbox" name="realHero" value="<c:out value='${human_being.realHero}' />"
                       style="margin-top: 15px; margin-left: 78px;" required>
                    <br>
                <label>Toothpick</label>
                <input type="checkbox" name="hasToothpick" style="margin-left: 76px;" required
                       value="<c:out value='${human_being.hasToothpick}' />">
                    <br>
<%--                <label>Impact Speed</label>--%>
                <input type="number" name="impactSpeed" placeholder="Impact Speed"
                       value="<c:out value='${human_being.impactSpeed}' />" required>
                    <br>
<%--                <label>soundtrackName</label>--%>
                <input type="text" name="soundtrackName" placeholder="Soundtrack name" style="margin-top: 15px;"
                       value="<c:out value='${human_being.soundtrackName}' />" required>
                    <br>
<%--                <label> minutesOfWaiting</label>--%>
                <input type="number" name="minutesOfWaiting" placeholder="Minutes of waiting" style="margin-top: 15px;"
                       value="<c:out value='${human_being.minutesOfWaiting}' />" required>
                    <br>
<%--                <label>weaponType</label>--%>
                <input type="text" name="weaponType" placeholder="Weapon type" style="margin-top: 15px;"
                       value="<c:out value='${human_being.weaponType}' />" required>
                    <br>
<%--                <label>carName</label>--%>
                <input type="text" name="car" placeholder="Car name" style="margin-top: 15px;"
                       value="<c:out value='${human_being.car.name}' />"> required
                    <br>
                <input type="submit" value="Save" style="margin-top: 15px; padding: 2px 63px; border-radius: 4px;">
            </form>
                <label><c:out value='${message}'/></label>
        </div>
    </div>
</section>
</body>
</html>