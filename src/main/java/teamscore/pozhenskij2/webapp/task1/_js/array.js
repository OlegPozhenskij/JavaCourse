function createTable() {
  let table = document.createElement("table");
  let tbody = document.createElement("tbody");

  for (let i = 1; i < 6; i++) {
    let row = document.createElement("tr");
    for (let j = 1; j < 6; j++) {
      let cell = document.createElement("td");
      cell.textContent = i + ":" + j;
      if (i == j) cell.classList.add("diagonal");

      row.appendChild(cell);
    }
    tbody.appendChild(row);
  }
  
  table.appendChild(tbody);
  document.body.appendChild(table);
}

createTable();
