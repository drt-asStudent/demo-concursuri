<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sun, 18-Jan-26
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle = "Login">
    <c:if test="${message != null}">
        <div class="alert alert-warning" role="alert">
                ${message}
        </div>
    </c:if>
    <form class="form-signin" action="<c:url value='/j_security_check'/>" method="post" autocomplete="off">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="j_username" class="sr-only">Username</label>
        <input type="text" id="j_username" autocomplete="" name="j_username" class="form-control" placeholder="Username" required autofocus value="">
        <label for="password" class="sr-only">Password</label>
        <input type="password" autocomplete="" id="password" name="j_password" class="form-control" placeholder="Password" required value="">
        <br>
        <div class="d-flex" style="gap: 0.5rem;">
            <button class="btn btn-lg btn-primary mr-2" type="submit">Sign in</button>
            <a class="btn btn-lg btn-success mr-2" href="${pageContext.request.contextPath}/AddUser">Crează cont</a>
            <a class="btn btn-lg btn-secondary" href="<c:url value='/'/>">Home</a>
        </div>
    </form>
    <c:if test="${param.notAccepted != null}">
        <script>
            alert("Sorry, your account has not been checked. Try again later!");
        </script>
    </c:if>
    <script>
        window.addEventListener("load", () => {
            const loginUser = document.getElementById("j_username");
            const loginPass = document.getElementById("password");
            if (loginUser) {
                loginUser.value = "";
            }
            if (loginPass) {
                loginPass.value = "";
            }
        });
    </script>
</t:pageTemplate>
