document.addEventListener("DOMContentLoaded", () => {
    // Selectăm toate butoanele care au atributul data-export
    const exportButtons = document.querySelectorAll("[data-export]");

    exportButtons.forEach(button => {
        button.addEventListener("click", () => {
            const format = button.dataset.export;   // "csv" sau "json"
            const tableId = button.dataset.table || "myTable"; // fallback
            const table = document.getElementById(tableId);

            if (!table) {
                console.error("Nu am găsit tabelul:", tableId);
                return;
            }

            if (format === "json") {
                exportTableToJSON(table);
            } else if (format === "csv") {
                exportTableToCSV(table);
            }
        });
    });
});

function exportTableToJSON(table) {
    const headers = [...table.querySelectorAll("thead th")].map(th =>
        th.innerText.trim()
    );

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

    const blob = new Blob([JSON.stringify(jsonData, null, 2)], {
        type: "application/json"
    });

    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "table-data.json";
    a.click();
    URL.revokeObjectURL(url);
}

function exportTableToCSV(table, filename = "table.csv") {
    let csvContent = "\uFEFF"; // BOM pentru Excel
    const rows = table.querySelectorAll("tr");

    rows.forEach(row => {
        const cells = row.querySelectorAll("th, td");
        const rowData = [];

        cells.forEach(cell => {
            const formField = cell.querySelector("input, textarea, select");
            let text = formField ? formField.value : cell.innerText;
            text = text.replace(/\s+/g, " ").trim().replace(/"/g, '""');
            rowData.push('"' + text + '"');
        });

        csvContent += rowData.join(",") + "\n";
    });

    const blob = new Blob([csvContent], {
        type: "text/csv;charset=utf-8;"
    });

    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = filename;
    a.click();
    URL.revokeObjectURL(url);
}