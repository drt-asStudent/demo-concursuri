<%--
  Created by IntelliJ IDEA.
  User: OBI-WAN KENOBI
  Date: Wed, 07-Jan-27
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="today" class="java.util.Date" scope="page"/>

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
                <th scope="col" class="text-center">Competition type</th>
                <th scope="col" class="text-center">Approach level</th>
                <th scope="col" class="text-center">Participare</th>
            </tr>
            </thead>
            <tbody>

            <fmt:formatDate var="todayYmd" value="${today}" pattern="yyyyMMdd"/>

            <c:forEach var="concursuri" items="${concursuri}">
                <fmt:formatDate var="desfasYmd" value="${concursuri.dataDesfasurare}" pattern="yyyyMMdd"/>
                <fmt:formatDate var="stopYmd" value="${concursuri.stopInscrieri}" pattern="yyyyMMdd"/>

                <c:if test="${desfasYmd >= todayYmd}">
                    <tr>

                        <td>${concursuri.nume}</td>
                        <td><fmt:formatDate value="${concursuri.dataDesfasurare}" pattern="EEE yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${concursuri.startInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${concursuri.stopInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="text-center">${concursuri.competitionType}</td>
                        <td class="text-center">${concursuri.nivel}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${stopYmd >= todayYmd and concursuri.registeredCount < concursuri.numarLocuri}">
                                    <a class="btn btn-sm btn-outline-primary"
                                       href="${pageContext.request.contextPath}/Participare?idc=${concursuri.id}">
                                        Participa
                                    </a>
                                </c:when>
                                <c:when test="${concursuri.registeredCount==concursuri.numarLocuri}">
                                    <span class="text-danger">
                                        Max. seats reached.
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-danger">
                                        Registration closed.
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>

            </tbody>
        </table>
    </div>
</t:pageTemplate>
