<%--
  Created by IntelliJ IDEA.
  User: dan_t
  Date: Sun, 22-Feb-26
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="CONCURSURILE MELE">
    <h1 class="mb-4 text-primary">CONCURSURILE MELE</h1>

    <c:if test="${empty concursuri}">
        <div class="alert alert-info" role="alert">
            Nu ai adaugat inca niciun concurs.
        </div>
    </c:if>

    <c:if test="${not empty concursuri}">
        <div class="container">
            <table id="myTable" class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th scope="col">Nume</th>
                    <th scope="col">Data desfasurare</th>
                    <th scope="col">Start inscrieri</th>
                    <th scope="col">Stop inscrieri</th>
                    <th scope="col" class="text-center">Tip</th>
                    <th scope="col" class="text-center">Nivel</th>
                    <th scope="col" class="text-center">Min/Max</th>
                    <th scope="col" class="text-center">Actiuni</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${concursuri}">
                    <tr>
                        <td>${c.nume}</td>
                        <td><fmt:formatDate value="${c.dataDesfasurare}" pattern="EEE yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${c.startInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${c.stopInscrieri}" pattern="EEE yyyy-MM-dd"/></td>
                        <td class="text-center">${c.competitionType}</td>
                        <td class="text-center">${c.nivel}</td>
                        <td class="text-center">${c.minPart}/${c.maxPart}</td>
                        <td class="text-center">
                            <a class="btn btn-sm btn-outline-secondary"
                               href="${pageContext.request.contextPath}/EditConcurs?idc=${c.id}">
                                EDIT
                            </a>
                            <form method="post" action="${pageContext.request.contextPath}/CancelConcurs"
                                  style="display:inline;">
                                <input type="hidden" name="idc" value="${c.id}">
                                <button type="submit" class="btn btn-sm btn-outline-danger"
                                        onclick="return confirm('Sigur vrei sa anulezi acest concurs?');"
                                        ${c.canceled == 1 ? 'disabled' : ''}>
                                    CANCEL COMPETITION
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <button id="exportButton" type="button">Export Table</button>
    </c:if>
    <script>
        const exportTableToCSV = (tableElement, filename = 'table.csv') => {
            let csvContent = "\uFEFF"; // UTF-8 BOM for Excel

            const rows = tableElement.querySelectorAll('tr');

            rows.forEach(row => {
                const rowText = row.innerText;
                if (rowText) {
                    const rowData = rowText.split('\t').map(value => {
                        const cleaned = value.replace(/\s+/g, ' ').trim().replace(/"/g, '""');
                        return '"' + cleaned + '"';
                    });
                    csvContent += rowData.join(',') + "\n";
                    return;
                }

                const rowData = [];
                const cells = row.querySelectorAll('th, td');

                cells.forEach(cell => {
                    const formField = cell.querySelector('input, textarea, select');
                    let cellText = formField ? formField.value : cell.innerText;
                    cellText = cellText.replace(/\s+/g, ' ').trim();
                    cellText = cellText.replace(/"/g, '""');
                    rowData.push('"' + cellText + '"');
                });

                csvContent += rowData.join(',') + "\n";
            });

            const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
            const url = URL.createObjectURL(blob);

            const link = document.createElement('a');
            link.href = url;
            link.download = filename;

            document.body.appendChild(link);
            link.click();

            document.body.removeChild(link);
            URL.revokeObjectURL(url);
        };

        document.addEventListener('DOMContentLoaded', () => {
            const exportButton = document.getElementById('exportButton');
            const tableToExport = document.getElementById('myTable');
            if (!exportButton || !tableToExport) {
                return;
            }
            exportButton.addEventListener('click', (event) => {
                event.preventDefault();
                if (tableToExport) {
                    exportTableToCSV(tableToExport, 'my_table_data.csv');
                } else {
                    console.error('Could not find the table to export.');
                }
            });
        });

    </script>
</t:pageTemplate>
