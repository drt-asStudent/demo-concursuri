<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="NOTAREA">
    <h1 class="mb-4 text-primary">NOTARE PARTICIPARI</h1>

    <c:if test="${param.saved == '1'}">
        <div class="alert alert-success">Nota salvata.</div>
    </c:if>
    <c:if test="${param.notFound == '1'}">
        <div class="alert alert-warning">Participarea nu a fost gasita.</div>
    </c:if>
    <c:if test="${param.error == '1'}">
        <div class="alert alert-danger">Date invalide pentru notare.</div>
    </c:if>

    <c:forEach var="concurs" items="${concursuri}">
        <h2 class="h5 mt-4">${concurs.nume}</h2>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th scope="col">Lucrare</th>
                <th scope="col">Descriere</th>
                <th scope="col">Nota</th>
                <th scope="col">Save</th>
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
                    <c:forEach var="participare" items="${participari}">
                        <tr>
                            <td>
                                <form id="notaForm_${participare.id}" method="post"
                                      action="${pageContext.request.contextPath}/Notare">
                                    <input type="hidden" name="idu" value="${participare.idu}">
                                    <input type="hidden" name="idc" value="${participare.idc}">
                                    <input type="hidden" name="lucrare" value="${participare.lucrare}">
                                </form>
                                ${participare.lucrare}
                            </td>
                            <td>${participare.descriere}</td>
                            <td>
                                <input form="notaForm_${participare.id}" type="number" name="nota"
                                       class="form-control form-control-sm"
                                       min="1" max="10" step="1"
                                       value="${participare.nota}">
                            </td>
                            <td class="text-center">
                                <button form="notaForm_${participare.id}" type="submit"
                                        class="btn btn-sm btn-primary">
                                    SAVE
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </c:forEach>
</t:pageTemplate>
