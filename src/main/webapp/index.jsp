<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="today" class="java.util.Date" scope="page"/>

<t:pageTemplate pageTitle="COMPETITII">
<style>
    .table-glass {
        --bs-table-bg: rgba(255, 255, 255, 0.55);            /* normal rows */
        --bs-table-striped-bg: rgba(255, 255, 255, 0.35);    /* striped rows */
        --bs-table-striped-color: #111;                      /* text on stripes */
        --bs-table-color: #111;                              /* text default */
        --bs-table-border-color: rgba(0, 0, 0, 0.2);
    }

    .clickable-row {
        cursor: pointer;
    }
</style>

    <div class="container mt-5">
        <h1 class="mb-4 text-primary">WELCOME TO THE FUTURE UNICORN FACTORY!</h1>
        <h2 class="mb-5 text-primary">Rise and shine!!!</h2>
        <h2 class="mb-5 text-secondary">PENDING COMPETITIONS</h2>

        <c:set var="isGuest" value="${pageContext.request.remoteUser == null}" />

        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover align-middle table-glass">
                <thead class="table-dark">
                <tr>
                    <%-- <th scope="col" class="text-center py-3">ID</th> --%>
                    <th scope="col" class="py-3">Competition title</th>
                    <th scope="col" class="py-3">Date the event is scheduled</th>
                    <th scope="col" class="py-3">Registration Starts</th>
                    <th scope="col" class="py-3">Registration Ends</th>
                    <th scope="col" class="text-center py-3">Competition type</th>
                    <th scope="col" class="py-3">Participants level</th>
                    <th scope="col" class="py-3">Seats</th>
                    <th scope="col" class="text-center py-3">Details</th>
                </tr>
                </thead>

                <tbody>
                <fmt:formatDate var="todayYmd" value="${today}" pattern="yyyyMMdd"/>

                <c:forEach var="concursuri" items="${concursuri}">
                    <fmt:formatDate var="desfasYmd" value="${concursuri.dataDesfasurare}" pattern="yyyyMMdd"/>
                    <c:if test="${desfasYmd >= todayYmd}">
                        <tr class="${isGuest ? 'clickable-row' : ''}"
                            data-href="${isGuest ? pageContext.request.contextPath.concat('/Login') : ''}">
                        <%-- <td class="text-center py-3">${concursuri.id}</td> --%>
                        <td class="py-3">${concursuri.nume}</td>
                        <td class="py-3" style="font-size: larger; font-weight: bold;"><fmt:formatDate value="${concursuri.dataDesfasurare}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="py-3"><fmt:formatDate value="${concursuri.startInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="py-3"><fmt:formatDate value="${concursuri.stopInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="text-center py-3">${concursuri.competitionType}</td>
                        <td class="py-3">${concursuri.nivel}</td>
                        <td class="py-3">
                            <c:out value="${concursuri.registeredCount != null ? concursuri.registeredCount : 0}" /> /
                            <c:out value="${concursuri.numarLocuri}" />
                        </td>
                        <td class="text-center py-3">
                            <a class="btn btn-sm btn-primary"
                               href="${pageContext.request.contextPath}${isGuest ? '/Login' : '/DetaliiConcurs?idc='.concat(concursuri.id)}">
                                DETAILS
                            </a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
<br><br><br>
<script type="text/javascript">
    document.addEventListener('click', function (e) {
        const row = e.target.closest('tr[data-href]');
        if (!row) return;

        // Don't trigger row redirect when clicking the DETAILS button/link
        if (e.target.closest('a, button, input, select, textarea, label')) return;

        const href = row.getAttribute('data-href');
        if (!href) return; // logged in => no redirect

        window.location.href = href;
    });
</script>
</t:pageTemplate>