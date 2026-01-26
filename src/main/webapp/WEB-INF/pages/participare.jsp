<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Mon, 27-Jan-26
  Time: 10:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Participare">
    <h1>Participare</h1>

    <c:if test="${message != null}">
        <div class="alert alert-warning" role="alert">
            ${message}
        </div>
    </c:if>

    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/Participare">
        <input type="hidden" name="idc" value="${idc}">

        <div class="mb-3">
            <label for="lucrare" class="form-label">Lucrare</label>
            <input type="text" class="form-control" id="lucrare" name="lucrare" required>
            <div class="invalid-feedback">
                Campul lucrare este obligatoriu.
            </div>
        </div>

        <div class="mb-3">
            <label for="descriere" class="form-label">Descriere</label>
            <textarea class="form-control" id="descriere" name="descriere" rows="3" required></textarea>
            <div class="invalid-feedback">
                Campul descriere este obligatoriu.
            </div>
        </div>

        <div class="mb-3">
            <label for="profesorCoordonator" class="form-label">Profesor coordonator</label>
            <input type="text" class="form-control" id="profesorCoordonator" name="profesorCoordonator" required>
            <div class="invalid-feedback">
                Campul profesor coordonator este obligatoriu.
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Trimite</button>
    </form>
</t:pageTemplate>
