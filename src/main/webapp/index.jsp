<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="COMPETITII">

    <h1>Welcome</h1>
    <h2>COMPETITIONS IN EFFECT</h2>

    <div class="container">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Competition title</th>
                <th scope="col">Event Date</th>
                <th scope="col">Registration Starts</th>
                <th scope="col">Registration Ends</th>
                <th scope="col">Software</th>
                <th scope="col">Hardware</th>
                <th scope="col">Participants level</th>
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