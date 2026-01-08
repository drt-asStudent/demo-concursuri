<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Thu, 08-Jan-26
  Time: 1:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Adăugare user">
    <h1>Adăugare utilizator</h1>

    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddUser">

        <!-- 1. id -->
        <div class="mb-3">
            <label for="id" class="form-label">Nr. crt.</label>
            <input type="text" class="form-control" id="id" name="id" required>
            <div class="invalid-feedback">
                Id-ul utilizatorului trebuie să fie UNIC.
            </div>
        </div>

        <!-- 2. nume -->
        <div class="mb-3">
            <label for="nume" class="form-label">Nume</label>
            <input type="text" class="form-control" id="nume" name="nume" required>
            <div class="invalid-feedback">
                Câmpul nume este obligatoriu.
            </div>
        </div>

        <!-- 3. prenume -->
        <div class="mb-3">
            <label for="prenume" class="form-label">Prenume</label>
            <input type="text" class="form-control" id="prenume" name="prenume" required>
            <div class="invalid-feedback">
                Câmpul prenume este obligatoriu.
            </div>
        </div>

        <!-- 4. email -->
        <div class="mb-3">
            <label for="eMail" class="form-label">eMail</label>
            <input type="text" class="form-control" id="eMail" name="eMail" required>
            <div class="invalid-feedback">
                Câmpul eMail este obligatoriu.
            </div>
        </div>

        <!-- 5. telefon -->
        <div class="mb-3">
            <label for="id" class="form-label">Telefon</label>
            <input type="text" class="form-control" id="telefon" name="telefon" required>
            <div class="invalid-feedback">
                Telefonul utilizatorului este obligatoriu.
            </div>
        </div>

        <!-- 6. rol-->
        <div class="mb-3">
            <label for="rol" class="form-label">Rol</label>
            <input type="text" class="form-control" id="rol" name="rol" required>
            <div class="invalid-feedback">
                Rolul utilizatorului este obligatoriu.
            </div>
        </div>

        <!-- 7. varsta -->
        <div class="mb-3">
            <label for="varsta" class="form-label">Varsta</label>
            <input type="text" class="form-control" id="varsta" name="varsta" required>
            <div class="invalid-feedback">
                Varsta utilizatorului este obligatorie.
            </div>
        </div>

        <!-- 9. Save Button -->
        <button type="submit" class="btn btn-primary">Save</button>

    </form>

    <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>

</t:pageTemplate>
