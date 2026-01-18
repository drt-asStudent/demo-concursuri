<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Wed, 07-Jan-26
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Adăugare  concurs">
    <h1>Adăugare concurs</h1>

    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddConcurs">

            <!-- 2. nume -->
        <div class="mb-3">
            <label for="nume" class="form-label">Genericul concursului</label>
            <input type="text" class="form-control" id="nume" name="nume" required>
            <div class="invalid-feedback">
                Câmpul nume este obligatoriu.
            </div>
        </div>

        <!-- 3. Data desfășurarii -->
        <div class="mb-3">
            <label for="dataDesfasurare" class="form-label">Data desfășurarii</label>
            <input type="date" class="form-control" id="dataDesfasurare" name="dataDesfasurare" required>
            <div class="invalid-feedback">
                Câmpul dataDesfasurare este obligatoriu.
            </div>
        </div>

        <!-- 4. Începere înscriere -->
        <div class="mb-3">
            <label for="startInscrieri" class="form-label">Începere înscriere</label>
            <input type="date" class="form-control" id="startInscrieri" name="startInscrieri" required>
            <div class="invalid-feedback">
                Câmpul startInscrieri este obligatoriu.
            </div>
        </div>

        <!-- 5. Sfârșit înscriere -->
        <div class="mb-3">
            <label for="stopInscrieri" class="form-label">Sfârșit înscriere</label>
            <input type="date" class="form-control" id="stopInscrieri" name="stopInscrieri" required>
            <div class="invalid-feedback">
                Câmpul stopInscrieri este obligatoriu.
            </div>
        </div>

        <!-- 6. isSoftware -->
        <div class="mb-3">
            <label for="isSoftware" class="form-label">Domeniul software?</label>
            <input type="number" class="form-control" id="isSoftware" name="isSoftware" required>
            <div class="invalid-feedback">
                Câmpul isSoftware este obligatoriu.
            </div>
        </div>

        <!-- 7. isHardware -->
        <div class="mb-3">
            <label for="isHardware" class="form-label">Domeniul hardware?</label>
            <input type="number" class="form-control" id="isHardware" name="isHardware" required>
            <div class="invalid-feedback">
                Câmpul isHardware este obligatoriu.
            </div>
        </div>

        <!-- 8. nivel -->
        <div class="mb-3">
            <label for="nivel" class="form-label">Nivel recomandat?</label>
            <input type="text" class="form-control" id="nivel" name="nivel" required>
            <div class="invalid-feedback">
                Câmpul isHardware este obligatoriu.
            </div>
        </div>

        <!-- 9. Save Button -->
        <button type="submit" class="btn btn-primary">Save</button>

    </form>

    <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>

</t:pageTemplate>