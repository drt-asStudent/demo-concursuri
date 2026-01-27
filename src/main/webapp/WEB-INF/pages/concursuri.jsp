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
    <h1 class="mb-4 text-primary">COMPETITIONS IN PROGRESS</h1>



    <div class="container">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>

                <th scope="col">Name</th>
                <th scope="col">Scheduled</th>
                <th scope="col">Registration Starts</th>
                <th scope="col">Registration Ends</th>
                <th scope="col" class="text-center">Software profile</th>
                <th scope="col" class="text-center">Hardware profile</th>
                <th scope="col" class="text-center">Approach level</th>
                <th scope="col" class="text-center">Participare</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="concursuri" items="${concursuri}">
                <tr>

                    <td>${concursuri.nume}</td>
                    <td><fmt:formatDate value="${concursuri.dataDesfasurare}" pattern="EEE yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${concursuri.startInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${concursuri.stopInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                    <td class="text-center">${concursuri.isSoftware ? "Yes" : "No"}</td>
                    <td class="text-center">${concursuri.isHardware ? "Yes" : "No"}</td>
                    <td class="text-center">${concursuri.nivel}</td>
                    <td class="text-center">
                        <a class="btn btn-sm btn-outline-primary"
                           href="${pageContext.request.contextPath}/Participare?idc=${concursuri.id}">
                            Participa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:pageTemplate>
