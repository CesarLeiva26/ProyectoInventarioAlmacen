document.addEventListener('DOMContentLoaded', function() {
  const filterForm = document.querySelector('.filter-form');
  
  filterForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const codProducto = document.getElementById('filter1').value;
    const nomProducto = document.getElementById('filter2').value;
    const ubicacion = document.getElementById('filter3').value;
    const lote = document.getElementById('filter4').value;
    const estado = document.getElementById('filter5').value;

    filterTable(codProducto, nomProducto, ubicacion, lote, estado);
  });


  function filterTable(codProducto, nomProducto, ubicacion, lote, estado) {
    const table = document.getElementById('tabla');
    const rows = table.querySelectorAll('tbody tr');

    rows.forEach(function(row) {
      const rowData = row.cells;
      const rowDataValues = Array.from(rowData).map(function(cell) {
        return cell.textContent;
      });

      const cumpleFiltros =
        (rowDataValues[0].includes(codProducto) || codProducto === '') &&
        (rowDataValues[1].includes(nomProducto) || nomProducto === '') &&
        (rowDataValues[3].includes(ubicacion) || ubicacion === '') &&
        (rowDataValues[4].includes(lote) || lote === '') &&
        (rowDataValues[7].includes(estado) || estado === '');

      row.style.display = cumpleFiltros ? 'table-row' : 'none';
    });
  }
});
