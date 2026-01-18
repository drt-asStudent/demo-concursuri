<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="USERS">
    <h1>Users List</h1>

    <a href="${pageContext.request.contextPath}/AddUser"
       class="btn btn-primary btn-lg mb-3">
        Adăugare utilizator
    </a>

    <div class="container mt-4">
        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">e-Mail</th>
                <th scope="col">Phone</th>
                <th scope="col">Status</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.prenume}</td>
                    <td>${user.nume}</td>
                    <td>${user.email}</td>
                    <td>${user.telefon}</td>
                    <td>${user.rol}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:pageTemplate>