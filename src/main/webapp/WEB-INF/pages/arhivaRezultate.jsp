<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sun, 22-Feb-26
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="ARHIVA REZULTATE">
    <h1 class="mb-4 text-primary">ARHIVA REZULTATE</h1>

    <c:if test="${empty concursuri}">
        <div class="alert alert-info">Nu exista concursuri inchise.</div>
    </c:if>

    <c:forEach var="concurs" items="${concursuri}">
        <h2 class="h5 mt-4 mb-3">${concurs.nume}</h2>

        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Lucrare</th>
                    <th scope="col">Descriere</th>
                    <th scope="col">Nota</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="participari" value="${participariByConcurs[concurs.id]}"/>
                <c:choose>
                    <c:when test="${empty participari}">
                        <tr>
                            <td colspan="4" class="text-center">Nu exista participari.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="p" items="${participari}" varStatus="st">
                            <tr>
                                <td>${st.index + 1}</td>
                                <td>${p.lucrare}</td>
                                <td>${p.descriere}</td>
                                <td>${p.nota}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>

        <c:set var="poze" value="${pozeByConcurs[concurs.id]}"/>
        <c:if test="${not empty poze}">
            <div class="d-flex flex-wrap gap-3 mt-3">
                <c:forEach var="poza" items="${poze}">
                    <img src="${pageContext.request.contextPath}/Image?id=${poza.id}"
                         alt="Poza concurs"
                         class="img-fluid"
                         style="max-width: 100%; height: auto;">
                </c:forEach>
            </div>
        </c:if>
    </c:forEach>
</t:pageTemplate>
