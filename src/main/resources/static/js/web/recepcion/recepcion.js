var filaCount = 1;
let selectEntidad;
let selectSucursal;
let respDoc;

var table = document.getElementById('tblentidad');
var tbody = table.getElementsByTagName('tbody')[0];

$(document).ready(function () {
  GetEntidades();
  GetEstado(filaCount);
  initTable();
});

document.getElementById('agregarFila').addEventListener('click', function () {
  filaCount++;
  initTable();
});

$('#cboEntidad').on('change', function () {
  $('#cbosucursal')
    .empty()
    .append(
      '<option selected="selected" value="Selecciona">Selecciona</option>'
    );
  if ($(this).val() == 'Selecciona') {
    $('.entidadName').val('');
    return;
  }
  let entidad = JSON.parse($(this).val());

  let entidadId = entidad.identidad;
  let entidadNombre = entidad.nombreEntidad;
  selectEntidad = entidad;

  $('.entidadName').val(entidadNombre);
  GetSucursales(entidadId);
});

$('#cbosucursal').on('change', function () {
  if ($(this).val() == 'Selecciona') {
    selectSucursal = 0;
    return;
  }
  let sucursal = JSON.parse($(this).val());
  selectSucursal = sucursal;
});

$('#cboDocResp').on('change', function () {
  if ($(this).val() == 'Selecciona') {
    docResp = 0;
    return;
  }
  let doc = $(this).val();
  console.log('doc', doc);
  respDoc = doc;
});

$('#orden_repecion').on('submit', async function (event) {
  event.preventDefault();
  let detalleRepcion = await captureDataFromRows();
  console.log('detalleRepcion', detalleRepcion);
  $('#formulario')[0].reset();
});

async function captureDataFromRows() {
  let recepcion = {
    tipoMov: $('#tipoMov').val(),
    fechaMov: $('#fechaMov').val(),
    idEntidad: JSON.parse($('#cboEntidad').val()).identidad,
    idSucursal: $('#cbosucursal').val(),
    docResp: $('#cboDocResp').val(),
    numDocResp: $('#numDocResp').val(),
    notas: $('#notas').val(),
    detallesRecepcion: [],
  };

  $('#tblentidad tbody tr').each(function (index) {
    let fila = $(this);
    if (fila.find('input').length === 0) return;
    let entrada = fila.find('[name="entrada"]').val();
    let estado = fila.find('[name="cboEstado"]').val();
    let idProducto = fila.find('[name="producto"]').attr('data-id-producto');
    let idLote = fila.find('[name="lote"]').attr('data-id-lote');
    let idUbicacion = fila.find('[name="ubicacion"]').attr('data-id-ubicacion');

    let filaDatos = {
      idproducto: Number(idProducto),
      idUbicacion: Number(idUbicacion),
      idLote: Number(idLote),
      idEstado: Number(estado),
      cantidad: Number(entrada),
    };

    recepcion.detallesRecepcion.push(filaDatos);
  });

  $.ajax({
    type: 'POST',
    url: '/recepcion/recepcion',
    contentType: 'application/json',
    data: JSON.stringify(recepcion),
    success: function (resultado) {
      alert(resultado.mensaje);
    },
  });

  return recepcion;
}

function initTable() {
  var newRow = document.createElement('tr');
  newRow.innerHTML = `
	   <td>${filaCount}</td>
	    <td>
	     <div class="autocomplete">
   			<input type="search" class="form-control autocomplete-input" name="producto" id="autocomplete-producto-${filaCount}" data-value="" placeholder="Buscar producto">
   		 	<div class="autocomplete-results"></div>
		 </div>
	    </td>
	    <td><p id="producto-${filaCount}"></p></td>
	    <td><input type="text" class="form-control form-control-sm" name="entrada" size="5"></td>
	    <td>
	      <div class="autocomplete">
	        <input type="search" class="form-control" name="ubicacion" id="autocomplete-ubicacion-${filaCount}" placeholder="Buscar ubicación">
	        <div class="autocomplete-results"></div>
	      </div>
	    </td>
	    <td>
	      <div class="autocomplete">
	        <input type="search" class="form-control" name="lote" id="autocomplete-lote-${filaCount}" placeholder="Buscar lote">
	        <div class="autocomplete-results"></div>
	      </div>
	    </td>
	    <td>Fecha de Fab. ${filaCount}</td>
	    <td>Fecha de Venc. ${filaCount}</td>
	    <td>
	      <select class="form-control" name="cboEstado" id="cboEstado-${filaCount}"> </select>
	    </td>
	    <td>
	      <button class="btn btn-danger btn-sm" onclick="eliminarFila(this)">
	        <i class="fas fa-trash-alt"></i>
	      </button>
	    </td>
	`;
  tbody.appendChild(newRow);

  buscarProducto(
    `#autocomplete-producto-${filaCount}`,
    '/producto/buscarProductoAutocompletado'
  );
  buscarUbicacion(
    `#autocomplete-ubicacion-${filaCount}`,
    '/ubicacion/buscarUbicacionAutocompletado'
  );
  buscarLote(
    `#autocomplete-lote-${filaCount}`,
    '/lote/buscarLoteAutocompletado'
  );
  GetEstado(filaCount);
}

function buscarLote(selector, url) {
  $(selector)
    .autocomplete({
      source: function (request, response) {
        $.ajax({
          url: url,
          type: 'GET',
          data: {
            term: request.term,
          },
          success: function (data) {
            var items = data.map(function (lote) {
              return {
                value: lote.idLote,
                label: lote.lote,
                fechafab: lote.fechaFab,
                fechaven: lote.fechaVen,
              };
            });
            response(items);
          },
          error: function () {
            response([]);
          },
        });
      },
      select: function (event, ui) {
        let lote = ui.item;
        let input = $(this);
        let fila = $(this).closest('tr');

        input.val(lote.label);
        input.attr('data-value', lote.label);
        input.attr('data-id-lote', lote.value);
        fila.find('td:nth-child(7)').text(lote.fechafab);
        fila.find('td:nth-child(8)').text(lote.fechaven);
        input.trigger('change');
        return false;
      },
    })
    .autocomplete('widget')
    .addClass('autocomplete-results custom-autocomplete-results')
    .css('background-color', '#E9E9E9')
    .css('list-style-type', 'none')
    .css('padding', '5px')
    .on('mouseover', 'li', function () {
      $(this).css('background-color', '#CBE2F3');
    })
    .on('mouseout', 'li', function () {
      $(this).css('background-color', '#E9E9E9');
    });
}

function buscarUbicacion(selector, url) {
  $(selector)
    .autocomplete({
      source: function (request, response) {
        $.ajax({
          url: url,
          type: 'GET',
          data: {
            term: request.term,
          },
          success: function (data) {
            console.log('data', data);
            var items = data.map(function (ubicacion) {
              return {
                value: ubicacion.idUbicacion,
                label: ubicacion.ubicacion,
              };
            });
            response(items);
          },
          error: function () {
            response([]);
          },
        });
      },
      select: function (event, ui) {
        let ubicacion = ui.item;
        let input = $(this);
        input.val(ubicacion.label);
        input.attr('data-value', ubicacion.label);
        input.attr('data-id-ubicacion', ubicacion.value);
        input.trigger('change');
        return false;
      },
    })
    .autocomplete('widget')
    .addClass('autocomplete-results custom-autocomplete-results')
    .css('background-color', '#E9E9E9')
    .css('list-style-type', 'none')
    .css('padding', '5px')
    .on('mouseover', 'li', function () {
      $(this).css('background-color', '#CBE2F3');
    })
    .on('mouseout', 'li', function () {
      $(this).css('background-color', '#E9E9E9');
    });
}

function buscarProducto(selector, url) {
  $(selector)
    .autocomplete({
      source: function (request, response) {
        $.ajax({
          url: url,
          type: 'GET',
          data: {
            term: request.term,
          },
          success: function (data) {
            var items = data.map(function (product) {
              return {
                value: product.idproducto,
                label: product.descripcion,
              };
            });
            response(items);
          },
          error: function () {
            response([]);
          },
        });
      },
      select: function (event, ui) {
        let producto = ui.item;
        let input = $(this);
        let fila = $(this).closest('tr');
        input.val(producto.label);
        input.attr('data-value', producto.label);
        input.attr('data-id-producto', producto.value);
        fila.find(`#producto-${filaCount}`).text(producto.label);
        input.trigger('change');
        return false;
      },
    })
    .autocomplete('widget')
    .addClass('autocomplete-results custom-autocomplete-results')
    .css('background-color', '#E9E9E9')
    .css('list-style-type', 'none')
    .css('padding', '5px')
    .on('mouseover', 'li', function () {
      $(this).css('background-color', '#CBE2F3');
    })
    .on('mouseout', 'li', function () {
      $(this).css('background-color', '#E9E9E9');
    });
}

function GetSucursales() {
  $.ajax({
    type: 'GET',
    url: '/sucursal/listarSucursales',
    dataType: 'json',
    success: function (resultado) {
      if (resultado.length > 0) {
        $.each(resultado, function (index, value) {
          $('#cbosucursal').append(
            `<option value='${value.idsucursal}'>${value.nomsucursal}</option>`
          );
        });
      }
    },
  });
}

function GetEstado(filaCount) {
  $.ajax({
    type: 'GET',
    url: '/estado/listarEstado',
    dataType: 'json',
    success: function (resultado) {
      if (resultado.length > 0) {
        console.log('zzzzzzz', resultado);
        var selectElement = $(`#cboEstado-${filaCount}`);
        selectElement.empty();
        $.each(resultado, function (index, value) {
          selectElement.append(
            `<option value='${value.idEstado}'>${value.nomEstado}</option>`
          );
        });
      }
    },
  });
}

function GetUbicacion() {
  $.ajax({
    type: 'GET',
    url: '/entidad/listarEntidades',
    dataType: 'json',
    success: function (resultado) {
      console.clear('re => ', resultado);
      if (resultado.length > 0) {
        $.each(resultado, function (index, value) {
          var entidadData = JSON.stringify(value);
          $('#cboEntidad').append(
            `<option value='${entidadData}'>${value.entidad}</option>`
          );
        });
      }
    },
  });
}

function GetEntidades() {
  $.ajax({
    type: 'GET',
    url: '/entidad/listarEntidades',
    dataType: 'json',
    success: function (resultado) {
      console.clear('re => ', resultado);
      if (resultado.length > 0) {
        $.each(resultado, function (index, value) {
          var entidadData = JSON.stringify(value);
          $('#cboEntidad').append(
            `<option value='${entidadData}'>${value.entidad}</option>`
          );
        });
      }
    },
  });
}

function eliminarFila(button) {
  var row = button.parentNode.parentNode;
  var tbody = row.parentNode;
  tbody.removeChild(row);
  actualizarNumerosFila(tbody);
}

function actualizarNumerosFila(tbody) {
  var filas = tbody.getElementsByTagName('tr');
  for (var i = 0; i < filas.length; i++) {
    filas[i].getElementsByTagName('td')[0].innerText = i + 1;
  }
  filaCount = filas.length;
}
