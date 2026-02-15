<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sun, 15-Feb-26
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="Detalii concurs">

    <div class="container mt-5">
        <h2 class="mb-4 text-primary">DETALII CONCURS</h2>

        <c:if test="${empty concurs}">
            <div class="alert alert-danger" role="alert">
                Concursul nu a fost găsit.
            </div>
        </c:if>

        <c:if test="${not empty concurs}">
            <div class="table-responsive mb-5">
                <table class="table table-bordered table-striped align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Nume</th>
                        <th>Detalii concurs</th>
                        <th>Data desfășurare</th>
                        <th>Start înscrieri</th>
                        <th>Stop înscrieri</th>
                        <th>Participări</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${concurs.nume}</td>
                        <td style="white-space: pre-wrap;">${concurs.detaliiConcurs}</td>
                        <td><fmt:formatDate value="${concurs.dataDesfasurare}" pattern="yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${concurs.startInscrieri}" pattern="yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${concurs.stopInscrieri}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <c:out value="${registeredCount}" /> / <c:out value="${concurs.maxPart}" />
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <h3 class="mb-3 text-secondary">Participări</h3>
            <div class="table-responsive">
                <table class="table table-bordered table-striped table-hover align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Lucrare</th>
                        <th>Descriere</th>
                        <th>Profesor coordonator</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${participari}">
                        <tr>
                            <td>${p.lucrare}</td>
                            <td style="white-space: pre-wrap;">${p.descriere}</td>
                            <td>${p.profesorCoordonator}</td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty participari}">
                        <tr>
                            <td colspan="3" class="text-center">Nu există participări pentru acest concurs.</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>

</t:pageTemplate>
