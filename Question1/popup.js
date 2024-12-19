document.getElementById("scrapeButton").addEventListener("click", async () => {
    // Execute script on the current tab to scrape table
    chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
      chrome.scripting.executeScript({
        target: { tabId: tabs[0].id },
        func: extractTableAndDownloadCSV
      });
    });
  });
  
  // Function to extract table and download CSV
  function extractTableAndDownloadCSV() {
    const table = document.querySelector("table"); // Assuming a single table on the page
    if (!table) {
      alert("No table found on this page.");
      return;
    }
  
    let csvData = "";
    const rows = table.querySelectorAll("tr");
  
    // Loop through rows and cells to generate CSV content
    rows.forEach((row) => {
      const cols = row.querySelectorAll("th, td");
      const rowData = [];
      cols.forEach((col) => rowData.push(`"${col.innerText}"`));
      csvData += rowData.join(",") + "\n";
    });
  
    // Create a downloadable link for the CSV
    const blob = new Blob([csvData], { type: "text/csv" });
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "table_data.csv";
    a.click();
    URL.revokeObjectURL(url); //To release blob object URL
  }
  
