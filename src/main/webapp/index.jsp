<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="COMPETITII">

    <div class="container mt-5">
        <h1 class="mb-4 text-primary">Welcome, at the future unicorns factory!</h1>
        <h2 class="mb-5 text-primary">Rise and shine!!!</h2>
        <h2 class="mb-5 text-secondary">COMING COMPETITIONS</h2>

        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover align-middle">
                <thead class="table-dark">
                <tr>
                    <th scope="col" class="text-center py-3">ID</th>
                    <th scope="col" class="py-3">Competition title</th>
                    <th scope="col" class="py-3">Event Date</th>
                    <th scope="col" class="py-3">Registration Starts</th>
                    <th scope="col" class="py-3">Registration Ends</th>
                    <th scope="col" class="text-center py-3">Competition type</th>
                    <th scope="col" class="py-3">Participants level</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="concursuri" items="${concursuri}">
                    <tr>
                        <td class="text-center py-3">${concursuri.id}</td>
                        <td class="py-3">${concursuri.nume}</td>
                        <td class="py-3"><fmt:formatDate value="${concursuri.dataDesfasurare}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="py-3"><fmt:formatDate value="${concursuri.startInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="py-3"><fmt:formatDate value="${concursuri.stopInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="text-center py-3">${concursuri.competitionType}</td>
                        <td class="py-3">${concursuri.nivel}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:pageTemplate>