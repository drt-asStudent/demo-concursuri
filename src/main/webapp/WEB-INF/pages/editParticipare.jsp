<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sat, 31-Jan-26
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="EDITARE ÎNSCRIERE">
    <h1 class="mb-4 text-primary">EDITARE ÎNSCRIERE</h1>

    <c:if test="${message != null}">
        <div class="alert alert-warning" role="alert">
            ${message}
        </div>
    </c:if>

    <form method="POST" action="${pageContext.request.contextPath}/EditParticipare">
        <input type="hidden" name="id" value="${participare.id}">

        <div class="mb-3">
            <label for="lucrare" class="form-label">Lucrare</label>
            <input type="text" class="form-control" id="lucrare" name="lucrare"
                   value="${participare.lucrare}" required>
        </div>

        <div class="mb-3">
            <label for="descriere" class="form-label">Descriere</label>
            <textarea class="form-control" id="descriere" name="descriere" rows="4" required>${participare.descriere}</textarea>
        </div>

        <div class="mb-3">
            <label for="profesorCoordonator" class="form-label">Profesor coordonator</label>
            <input type="text" class="form-control" id="profesorCoordonator" name="profesorCoordonator"
                   value="${participare.profesorCoordonator}" required>
        </div>

        <button type="submit" class="btn btn-primary">SAVE</button>
        <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/InscrierileMele">Cancel</a>
    </form>
</t:pageTemplate>
