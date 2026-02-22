<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sun, 22-Feb-26
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="Editare concurs">
    <h1 class="mb-4 text-primary">Editare concurs</h1>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <c:out value="${error}" />
        </div>
    </c:if>

    <fmt:formatDate var="dataDesfasurareStr" value="${concurs.dataDesfasurare}" pattern="yyyy-MM-dd"/>
    <fmt:formatDate var="startInscrieriStr" value="${concurs.startInscrieri}" pattern="yyyy-MM-dd"/>
    <fmt:formatDate var="stopInscrieriStr" value="${concurs.stopInscrieri}" pattern="yyyy-MM-dd"/>

    <form class="needs-validation" novalidate method="POST"
          action="${pageContext.request.contextPath}/EditConcurs"
          enctype="multipart/form-data">

        <input type="hidden" name="idc" value="${concurs.id}">

        <div class="mb-3">
            <label for="nume" class="form-label">Genericul concursului</label>
            <input type="text" class="form-control" id="nume" name="nume" value="${concurs.nume}" required>
            <div class="invalid-feedback">
                Campul nume este obligatoriu.
            </div>
        </div>

        <div class="mb-3">
            <label for="detaliiConcurs" class="form-label">Detalii concurs</label>
            <input type="text" class="form-control" id="detaliiConcurs" name="detaliiConcurs"
                   value="${concurs.detaliiConcurs}" maxlength="255">
        </div>

        <div class="mb-3">
            <label for="dataDesfasurare" class="form-label">Data desfasurarii</label>
            <input type="date" class="form-control" id="dataDesfasurare" name="dataDesfasurare"
                   value="${dataDesfasurareStr}" required>
            <div class="invalid-feedback">
                Campul dataDesfasurare este obligatoriu.
            </div>
        </div>

        <div class="mb-3">
            <label for="startInscrieri" class="form-label">Incepere inscriere</label>
            <input type="date" class="form-control" id="startInscrieri" name="startInscrieri"
                   value="${startInscrieriStr}" required>
            <div class="invalid-feedback">
                Campul startInscrieri este obligatoriu.
            </div>
        </div>

        <div class="mb-3">
            <label for="stopInscrieri" class="form-label">Sfarsit inscriere</label>
            <input type="date" class="form-control" id="stopInscrieri" name="stopInscrieri"
                   value="${stopInscrieriStr}" required>
            <div class="invalid-feedback">
                Campul stopInscrieri este obligatoriu.
            </div>
        </div>

        <div class="mb-3">
            <label for="competitionType" class="form-label">Competition type</label>
            <select class="form-select" id="competitionType" name="competitionType" required>
                <option value="" disabled>-- Select --</option>
                <option value="software" ${concurs.competitionType == 'software' ? 'selected' : ''}>software</option>
                <option value="hardware" ${concurs.competitionType == 'hardware' ? 'selected' : ''}>hardware</option>
                <option value="mixed" ${concurs.competitionType == 'mixed' ? 'selected' : ''}>mixed</option>
            </select>
            <div class="invalid-feedback">
                Campul competitionType este obligatoriu.
            </div>
        </div>

        <div class="mb-3">
            <label for="nivel" class="form-label">Nivel recomandat?</label>
            <select class="form-select" id="nivel" name="nivel" required>
                <option value="" disabled>-- Select --</option>
                <option value="external" ${concurs.nivel == 'external' ? 'selected' : ''}>external</option>
                <option value="ULBSibiu" ${concurs.nivel == 'ULBSibiu' ? 'selected' : ''}>ULBSibiu</option>
            </select>
            <div class="invalid-feedback">
                Campul nivel este obligatoriu.
            </div>
        </div>

        <div class="mb-3">
            <label for="minPart" class="form-label">Min participants</label>
            <input type="number" class="form-control" id="minPart" name="minPart"
                   min="1" value="${concurs.minPart}" required>
            <div class="invalid-feedback">
                Min participants must be <= Max participants.
            </div>
        </div>

        <div class="mb-3">
            <label for="maxPart" class="form-label">Max participants</label>
            <input type="number" class="form-control" id="maxPart" name="maxPart"
                   min="1" value="${concurs.maxPart}" required>
            <div class="invalid-feedback">
                Max participants must be >= Min participants.
            </div>
        </div>

        <div class="mb-3">
            <label for="poza" class="form-label">Poster (prezentare concurs)</label>
            <input type="file" class="form-control" id="poza" name="poza" accept="image/*">
            <div class="form-text">Alege o imagine (JPG/PNG/etc). Se va salva în tabelul <code>poze</code>.</div>

            <div class="mt-3">
                <c:if test="${not empty pozaPrezentareId}">
                    <img id="pozaPreview" alt="Preview poza" src="${pageContext.request.contextPath}/Image?id=${pozaPrezentareId}" style="max-width: 320px; display: inline-block;" class="img-thumbnail"/>
                </c:if>
                <c:if test="${empty pozaPrezentareId}">
                    <img id="pozaPreview" alt="Preview poza" style="max-width: 320px; display: none;" class="img-thumbnail"/>
                </c:if>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
<br><br><br>
    <script type="text/javascript">
        (function () {
            const minEl = document.getElementById('minPart');
            const maxEl = document.getElementById('maxPart');
            if (!minEl || !maxEl) return;

            function validateMinMax() {
                const min = parseInt(minEl.value, 10);
                const max = parseInt(maxEl.value, 10);

                minEl.setCustomValidity('');
                maxEl.setCustomValidity('');

                if (!Number.isNaN(min) && !Number.isNaN(max) && min > max) {
                    minEl.setCustomValidity('minPart must be <= maxPart');
                    maxEl.setCustomValidity('maxPart must be >= minPart');
                }
            }

            minEl.addEventListener('input', validateMinMax);
            maxEl.addEventListener('input', validateMinMax);
            validateMinMax();
        })();
    </script>

    <script type="text/javascript">
        (function () {
            const input = document.getElementById('poza');
            const img = document.getElementById('pozaPreview');
            if (!input || !img) return;

            input.addEventListener('change', function () {
                const file = input.files && input.files[0];
                if (!file) {
                    img.style.display = 'none';
                    img.removeAttribute('src');
                    return;
                }
                if (!file.type || !file.type.startsWith('image/')) {
                    img.style.display = 'none';
                    img.removeAttribute('src');
                    return;
                }

                const reader = new FileReader();
                reader.onload = function (e) {
                    img.src = e.target.result;
                    img.style.display = 'inline-block';
                };
                reader.readAsDataURL(file);
            });
        })();
    </script>

</t:pageTemplate>
