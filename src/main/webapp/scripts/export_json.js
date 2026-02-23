// === EXPORT JSON ===
document.getElementById("exportButton")?.addEventListener("click", function () {
    const table = document.getElementById("myFixedTable");
    if (!table) return;

    const headers = [...table.querySelectorAll("thead th")].map(th => th.innerText.trim());
    const rows = table.querySelectorAll("tbody tr");

    const jsonData = [];

    rows.forEach(row => {
        const cells = row.querySelectorAll("td");
        const rowData = {};
        headers.forEach((header, i) => {
            rowData[header] = cells[i]?.innerText.trim() ?? "";
        });
        jsonData.push(rowData);
    });

    const jsonString = JSON.stringify(jsonData, null, 2);
    const blob = new Blob([jsonString], { type: "application/json" });
    const url = URL.createObjectURL(blob);

    const a = document.createElement("a");
    a.href = url;
    a.download = "table-data.json";
    a.click();

    URL.revokeObjectURL(url);
});


// === FUNCȚIE EXPORT CSV ===
function exportTableToCSV(tableElement, filename = 'table.csv') {
    let csvContent = "\uFEFF"; // UTF‑8 BOM pentru Excel
    const rows = tableElement.querySelectorAll('tr');

    rows.forEach(row => {
        const cells = row.querySelectorAll('th, td');
        const rowData = [];

        cells.forEach(cell => {
            const formField = cell.querySelector('input, textarea, select');
            let cellText = formField ? formField.value : cell.innerText;
            cellText = cellText.replace(/\s+/g, ' ').trim().replace(/"/g, '""');
            rowData.push('"' + cellText + '"');
        });

        csvContent += rowData.join(',') + "\n";
    });

    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);

    const link = document.createElement('a');
    link.href = url;
    link.download = filename;
    link.click();

    URL.revokeObjectURL(url);
}


// === EXPORT CSV BUTTON ===
document.addEventListener('DOMContentLoaded', () => {
    const exportButton = document.getElementById('exportBtx');
    const tableToExport = document.getElementById('myTable');

    if (!exportButton || !tableToExport) return;

    exportButton.addEventListener('click', (event) => {
        event.preventDefault();
        exportTableToCSV(tableToExport, 'my_table_data.csv');
    });
});
