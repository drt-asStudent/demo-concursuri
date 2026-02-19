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

    <style>
        /* Ensure BOTH header and body cells are vertically centered */
        .table th,
        .table td {
            vertical-align: middle !important;
        }

        /* Fixed, equal width date columns + don't wrap dates */
        .date-col {
            width: 8rem;
        }
        .date-cell {

        }
    </style>

    <div class="container mt-5">
        <div class="row align-items-start mb-4">
            <div class="col-12 col-lg-7">
                <h2 class="mb-4 text-primary">DETALIILE CONCURSULUI: </h2>
                <h2 style="font-weight: bold">"${concurs.nume}"</h2>
            </div>

            <div class="col-12 col-lg-5 text-lg-end">
                <p>E NEVOIE DE AUTENTIFICARE PENTRU A SOLICITA PARTICIPAREA LA UN CONCURS.</p>
            </div>
        </div>

        <c:if test="${empty concurs}">
            <div class="alert alert-danger" role="alert">
                Concursul nu a fost găsit.
            </div>
        </c:if>

        <c:if test="${not empty concurs}">
            <div class="table-responsive mb-5">
                <table class="table table-bordered table-striped align-middle">
                    <colgroup>
                        <col> <!-- Detalii concurs -->
                        <col class="date-col"> <!-- Data desfășurare -->
                        <col class="date-col"> <!-- Data început înscrieri -->
                        <col class="date-col"> <!-- Data finalizare înscrieri -->
                        <col> <!-- Nr. min. participanți -->
                        <col> <!-- Nr. max. participanți -->
                        <col> <!-- Înscriși -->
                    </colgroup>
                    <thead class="table-dark">
                    <tr>

                        <th>Tematica propusă</th>
                        <th class="date-cell">Data desfășurare</th>
                        <th class="date-cell">Data început înscrieri</th>
                        <th class="date-cell">Data finalizare înscrieri</th>
                        <th>Nr. min. participanți</th>
                        <th>Nr. max. participanți</th>
                        <th>Înscriși</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>

                        <td style="white-space: pre-wrap;">${concurs.detaliiConcurs}</td>
                        <td class="date-cell"><fmt:formatDate value="${concurs.dataDesfasurare}" pattern="yyyy-MM-dd"/></td>
                        <td class="date-cell"><fmt:formatDate value="${concurs.startInscrieri}" pattern="yyyy-MM-dd"/></td>
                        <td class="date-cell"><fmt:formatDate value="${concurs.stopInscrieri}" pattern="yyyy-MM-dd"/></td>
                        <td>${concurs.minPart}</td>
                        <td>${concurs.maxPart}</td>

                        <td>
                            <c:out value="${registeredCount}" /> / <c:out value="${concurs.maxPart}" />
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <h3 class="mb-3 text-secondary">LUCRĂRI ÎNSCRISE:</h3>
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
<br><br><br>
</t:pageTemplate>
