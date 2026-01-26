<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Mon, 26-Jan-26
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Firme">
    <h1 class="mb-4 text-primary">Firme</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Firma</th>
            <th>Descriere</th>
            <th>Reprezentant</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="firma" items="${firme}">
            <tr>
                <td>${firma.firma}</td>
                <td>${firma.descriere}</td>
                <td>
                    <c:forEach var="rep" items="${firma.reprezentanti}">
                        ${rep.user.nume}<br>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>
