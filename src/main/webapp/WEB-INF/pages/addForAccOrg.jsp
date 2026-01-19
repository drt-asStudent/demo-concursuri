<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sun, 18-Jan-26
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="AWAITING">
  <h1>Requesting organizers acceptance</h1>

  <div class="container mt-4">
    <table class="table table-bordered table-striped table-hover">
      <thead class="table-dark">
      <tr>
        <th scope="col">ID</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">e-Mail</th>
        <th scope="col">Phone</th>
        <th scope="col">Status</th>

        <th scope="col">Accept</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="user" items="${users}">
        <tr>
          <td>${user.id}</td>
          <td>${user.prenume}</td>
          <td>${user.nume}</td>
          <td>${user.eMail}</td>
          <td>${user.telefon}</td>
          <td>${user.rol}</td>

          <td>
            <form method="POST" action="${pageContext.request.contextPath}/AddAcceptOrganizers">
              <input type="hidden" name="user_id" value="${user.id}">
              <button type="submit" class="btn btn-success btn-sm">ACCEPT</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</t:pageTemplate>
