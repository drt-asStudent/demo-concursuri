<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Wed, 07-Jan-26
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="CONCURSURI">
    <h1>COMPETITIONS IN EFFECT</h1>
    <div class="container text-center">
        <c:forEach var="concursuri" items="${concursuri}">
            <div class="row">
                <div class="col">
                        ${concursuri.id}
                </div>
                <div class="col">
                        ${concursuri.nume}
                </div>
                <div class="col">
                        ${concursuri.dataDesfasurare}
                </div>
                <div class="col">
                        ${concursuri.startInscrieri}
                </div>
                <div class="col">
                        ${concursuri.stopInscrieri}
                </div>
                <div class="col">
                        ${concursuri.isSoftware}
                </div>
                <div class="col">
                        ${concursuri.isHardware}
                </div>
            </div>
        </c:forEach>


    </div>
</t:pageTemplate>
