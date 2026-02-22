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

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <c:out value="${error}" />
        </div>
    </c:if>

    <form class="needs-validation" novalidate method="POST"
          action="${pageContext.request.contextPath}/AddConcurs"
          enctype="multipart/form-data">

            <!-- 2. nume -->
        <div class="mb-3">
            <label for="nume" class="form-label">Genericul concursului</label>
            <input type="text" class="form-control" id="nume" name="nume" required>
            <div class="invalid-feedback">
                Câmpul nume este obligatoriu.
            </div>
        </div>

        <!-- detaliiConcurs -->
        <div class="mb-3">
            <label for="detaliiConcurs" class="form-label">Detalii concurs</label>
            <input type="text" class="form-control" id="detaliiConcurs" name="detaliiConcurs" maxlength="255">
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

        <!-- 6. competitionType -->
        <div class="mb-3">
            <label for="competitionType" class="form-label">Competition type</label>
            <select class="form-select" id="competitionType" name="competitionType" required>
                <option value="" selected disabled>-- Select --</option>
                <option value="software">software</option>
                <option value="hardware">hardware</option>
                <option value="mixed">mixed</option>
            </select>
            <div class="invalid-feedback">
                Câmpul competitionType este obligatoriu.
            </div>
        </div>

        <!-- 8. nivel -->
        <div class="mb-3">
            <label for="nivel" class="form-label">Nivel recomandat?</label>
            <select class="form-select" id="nivel" name="nivel" required>
                <option value="" selected disabled>-- Select --</option>
                <option value="external">external</option>
                <option value="ULBSibiu">ULBSibiu</option>
            </select>
            <div class="invalid-feedback">
                Câmpul nivel este obligatoriu.
            </div>
        </div>

        <!-- 9a. minPart -->
        <div class="mb-3">
            <label for="minPart" class="form-label">Min participants</label>
            <input type="number" class="form-control" id="minPart" name="minPart" min="1" required>
            <div class="invalid-feedback">
                Min participants must be <= Max participants.
            </div>
        </div>

        <!-- 9b. maxPart -->
        <div class="mb-3">
            <label for="maxPart" class="form-label">Max participants</label>
            <input type="number" class="form-control" id="maxPart" name="maxPart" min="1" required>
            <div class="invalid-feedback">
                Max participants must be >= Min participants.
            </div>
        </div>

        <!-- 10: poza (upload + preview) -->
        <div class="mb-3">
            <label for="poza" class="form-label">Poză (prezentare concurs)</label>
            <input type="file" class="form-control" id="poza" name="poza" accept="image/*">
            <div class="form-text">Alege o imagine (JPG/PNG/etc). Se va salva în tabelul <code>poze</code>.</div>

            <div class="mt-3">
                <img id="pozaPreview" alt="Preview poză" style="max-width: 320px; display: none;" class="img-thumbnail"/>
            </div>
        </div>

        <!-- 10. Save Button -->
        <button type="submit" class="btn btn-primary">Save</button>

    </form>
    <br><br><br>
    <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>
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

