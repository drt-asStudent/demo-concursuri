<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sat, 31-Jan-26
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="ÎNSCRIERILE MELE">
    <h1 class="mb-4 text-primary">ÎNSCRIERILE MELE</h1>

    <c:if test="${empty inscrieri}">
        <div class="alert alert-info" role="alert">
            Nu ai înscrieri încă.
        </div>
    </c:if>

    <c:if test="${not empty inscrieri}">
        <div class="container">
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th scope="col">Concurs</th>
                    <th scope="col">Lucrare</th>
                    <th scope="col">Descriere</th>
                    <th scope="col">Profesor coordonator</th>
                    <th scope="col" class="text-center">Edit</th>
                    <th scope="col" class="text-center">MERITĂ?</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${inscrieri}">
                    <tr>
                        <td>${p.concursNume}</td>
                        <td>${p.lucrare}</td>
                        <td>${p.descriere}</td>
                        <td>${p.profesorCoordonator}</td>
                        <td class="text-center">
                            <a class="btn btn-sm btn-outline-secondary"
                               href="${pageContext.request.contextPath}/EditParticipare?id=${p.participareId}">
                                EDIT
                            </a>
                        </td>
                        <td class="text-center">
                            <form method="post" action="${pageContext.request.contextPath}/RenuntParticipare"
                                  style="display:inline;">
                                <input type="hidden" name="id" value="${p.participareId}">
                                <button type="submit" class="btn btn-sm btn-outline-danger"
                                        onclick="return confirm('Sigur vrei să renunți la această înscriere?');">
                                    RENUNT
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
