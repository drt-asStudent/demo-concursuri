<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Wed, 07-Jan-26
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Adăugare  concurs">
    <h1 class="mb-4 text-primary">Adăugare concurs</h1>

    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddConcurs">

            <!-- 2. nume -->
        <div class="mb-3">
            <label for="nume" class="form-label">Genericul concursului</label>
            <input type="text" class="form-control" id="nume" name="nume" required>
            <div class="invalid-feedback">
                CÃ¢mpul nume este obligatoriu.
            </div>
        </div>

        <!-- 3. Data desfășurarii -->
        <div class="mb-3">
            <label for="dataDesfasurare" class="form-label">Data desfășurarii</label>
            <input type="date" class="form-control" id="dataDesfasurare" name="dataDesfasurare" required>
            <div class="invalid-feedback">
                CÃ¢mpul dataDesfasurare este obligatoriu.
            </div>
        </div>

        <!-- 4. ÃŽncepere Ã®nscriere -->
        <div class="mb-3">
            <label for="startInscrieri" class="form-label">Începere înscriere</label>
            <input type="date" class="form-control" id="startInscrieri" name="startInscrieri" required>
            <div class="invalid-feedback">
                CÃ¢mpul startInscrieri este obligatoriu.
            </div>
        </div>

        <!-- 5. SfÃ¢rÈ™it Ã®nscriere -->
        <div class="mb-3">
            <label for="stopInscrieri" class="form-label">Sfârșit înscriere</label>
            <input type="date" class="form-control" id="stopInscrieri" name="stopInscrieri" required>
            <div class="invalid-feedback">
                CÃ¢mpul stopInscrieri este obligatoriu.
            </div>
        </div>

        <!-- 6. isSoftware -->
        <div class="mb-3">
            <label for="isSoftware" class="form-label">Domeniul software?</label>
            <select class="form-select" id="isSoftware" name="isSoftware" required>
                <option value="" selected disabled>-- Select --</option>
                <option value="1">Yes</option>
                <option value="0">No</option>
            </select>
            <div class="invalid-feedback">
                CÃ¢mpul isSoftware este obligatoriu.
            </div>
        </div>

        <!-- 7. isHardware -->
        <div class="mb-3">
            <label for="isHardware" class="form-label">Domeniul hardware?</label>
            <select class="form-select" id="isHardware" name="isHardware" required>
                <option value="" selected disabled>-- Select --</option>
                <option value="1">Yes</option>
                <option value="0">No</option>
            </select>
            <div class="invalid-feedback">
                CÃ¢mpul isHardware este obligatoriu.
            </div>
        </div>

        <!-- 8. nivel -->
        <div class="mb-3">
            <label for="nivel" class="form-label">Nivel recomandat?</label>
            <select class="form-select" id="nivel" name="nivel" required>
                <option value="" selected disabled>-- Select --</option>
                <option value="external">external</option>
                <option value="student">student</option>
            </select>
            <div class="invalid-feedback">
                CÃ¢mpul nivel este obligatoriu.
            </div>
        </div>

        <!-- 9. Save Button -->
        <button type="submit" class="btn btn-primary">Save</button>

    </form>

    <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>

</t:pageTemplate>

