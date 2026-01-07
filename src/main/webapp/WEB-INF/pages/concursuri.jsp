<%--
  Created by IntelliJ IDEA.
  User: OBI-WAN KENOBI
  Date: Wed, 07-Jan-27
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="CONCURSURI">
    <h1>COMPETITIONS IN EFFECT</h1>

    <!-- Buton ADD COMPETITION -->
    <a href="${pageContext.request.contextPath}/AddConcurs"
       class="btn btn-primary btn-lg mb-3">
        Adăugare concurs
    </a>

    <div class="container">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Event Date</th>
                <th scope="col">Registration Starts</th>
                <th scope="col">Registration Ends</th>
                <th scope="col">Software</th>
                <th scope="col">Hardware</th>
                <th scope="col">Nivel</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="concursuri" items="${concursuri}">
                <tr>
                    <td>${concursuri.id}</td>
                    <td>${concursuri.nume}</td>
                    <td><fmt:formatDate value="${concursuri.dataDesfasurare}" pattern="EEE yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${concursuri.startInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${concursuri.stopInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                    <td>${concursuri.isSoftware ? "Yes" : "No"}</td>
                    <td>${concursuri.isHardware ? "Yes" : "No"}</td>
                    <td>${concursuri.nivel}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:pageTemplate>