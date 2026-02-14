<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sun, 15-Feb-26
  Time: 12:03 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="Acceptare Lucrări">
    <h1 class="mb-4 text-primary">ACCEPTARE LUCRĂRI</h1>

    <c:if test="${empty concursuri}">
        <div class="alert alert-info">
            Nu există concursuri publicate de tine (sau nu există înscrieri).
        </div>
    </c:if>

    <div class="accordion" id="accConcursuri">
        <c:forEach var="concurs" items="${concursuri}" varStatus="st">
            <c:set var="pid" value="${participariByConcurs[concurs.id]}" />

            <div class="accordion-item">
                <h2 class="accordion-header" id="heading${st.index}">
                    <button class="accordion-button ${st.first ? '' : 'collapsed'}"
                            type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#collapse${st.index}"
                            aria-expanded="${st.first}"
                            aria-controls="collapse${st.index}">
                        <span class="fw-semibold">
                            <c:out value="${concurs.nume}" />
                        </span>
                        <span class="ms-3 text-muted">
                            (<fmt:formatDate value="${concurs.dataDesfasurare}" pattern="yyyy-MM-dd"/>)
                        </span>
                    </button>
                </h2>

                <div id="collapse${st.index}"
                     class="accordion-collapse collapse ${st.first ? 'show' : ''}"
                     aria-labelledby="heading${st.index}"
                     data-bs-parent="#accConcursuri">
                    <div class="accordion-body">

                        <c:choose>
                            <c:when test="${empty pid}">
                                <div class="text-muted">Nu există înscrieri la acest concurs.</div>
                            </c:when>
                            <c:otherwise>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped align-middle">
                                        <thead class="table-dark">
                                        <tr>
                                            <th>Lucrare</th>
                                            <th>Descriere</th>
                                            <th>Profesor coordonator</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="p" items="${pid}">
                                            <tr>
                                                <td><c:out value="${p.lucrare}" /></td>
                                                <td><c:out value="${p.descriere}" /></td>
                                                <td><c:out value="${p.profesorCoordonator}" /></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</t:pageTemplate>
