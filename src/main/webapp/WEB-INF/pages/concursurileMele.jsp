<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sun, 22-Feb-26
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="CONCURSURILE MELE">
    <h1 class="mb-4 text-primary">CONCURSURILE MELE</h1>

    <c:if test="${empty concursuri}">
        <div class="alert alert-info" role="alert">
            Nu ai adaugat inca niciun concurs.
        </div>
    </c:if>

    <c:if test="${not empty concursuri}">
        <div class="container">
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th scope="col">Nume</th>
                    <th scope="col">Data desfasurare</th>
                    <th scope="col">Start inscrieri</th>
                    <th scope="col">Stop inscrieri</th>
                    <th scope="col" class="text-center">Tip</th>
                    <th scope="col" class="text-center">Nivel</th>
                    <th scope="col" class="text-center">Min/Max</th>
                    <th scope="col" class="text-center">Actiuni</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${concursuri}">
                    <tr>
                        <td>${c.nume}</td>
                        <td><fmt:formatDate value="${c.dataDesfasurare}" pattern="EEE yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${c.startInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${c.stopInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="text-center">${c.competitionType}</td>
                        <td class="text-center">${c.nivel}</td>
                        <td class="text-center">${c.minPart}/${c.maxPart}</td>
                        <td class="text-center">
                            <a class="btn btn-sm btn-outline-secondary"
                               href="${pageContext.request.contextPath}/EditConcurs?idc=${c.id}">
                                EDIT
                            </a>
                            <form method="post" action="${pageContext.request.contextPath}/CancelConcurs"
                                  style="display:inline;">
                                <input type="hidden" name="idc" value="${c.id}">
                                <button type="submit" class="btn btn-sm btn-outline-danger"
                                        onclick="return confirm('Sigur vrei sa anulezi acest concurs?');"
                                        ${c.canceled == 1 ? 'disabled' : ''}>
                                    CANCEL COMPETITION
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</t:pageTemplate>
