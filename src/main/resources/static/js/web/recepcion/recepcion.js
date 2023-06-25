var filaCount = 1;
let selectEntidad;
let selectSucursal;
let respDoc;

var table = document.getElementById('tblentidad');
var tbody = table.getElementsByTagName('tbody')[0];

$(document).ready(function() {
	GetEntidades();
	GetEstado(filaCount);
	initTable();
});

document.getElementById('agregarFila').addEventListener('click', function() {
	filaCount++;
	initTable();
});

$('#cboEntidad').on('change', function() {
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

$('#cbosucursal').on('change', function() {
	if ($(this).val() == 'Selecciona') {
		selectSucursal = 0;
		return;
	}
	let sucursal = JSON.parse($(this).val());
	selectSucursal = sucursal;
});

$('#cboDocResp').on('change', function() {
	if ($(this).val() == 'Selecciona') {
		docResp = 0;
		return;
	}
	let doc = $(this).val();
	console.log('doc', doc);
	respDoc = doc;
});

$('#orden_repecion').on('submit', async function(event) {
	event.preventDefault();
	let detalleRepcion = await captureDataFromRows();
	$('#orden_repecion')[0].reset();
	window.location.href = "http://localhost:8080/recepcion/orden_recepcion";
});

async function captureDataFromRows() {
	let recepcion = {
		tipomov: $('#tipoMov').val(),
		fechamov: $('#fechaMov').val(),
		identidad: JSON.parse($('#cboEntidad').val()).identidad,
		idsucursal: $('#cbosucursal').val(),
		docresp: $('#cboDocResp').val(),
		numdocresp: $('#numDocResp').val(),
		notas: $('#notas').val(),
		detallesrecepcion: [],
	};

	$('#tblentidad tbody tr').each(function(index) {
		let fila = $(this);
		if (fila.find('input').length === 0) return;
		let entrada = fila.find('[name="entrada"]').val();
		let estado = fila.find('[name="cboEstado"]').val();
		let idProducto = fila.find('[name="producto"]').attr('data-id-producto');
		let idLote = fila.find('[name="lote"]').attr('data-id-lote');
		let idUbicacion = fila.find('[name="ubicacion"]').attr('data-id-ubicacion');

		let filaDatos = {
			idproducto: Number(idProducto),
			idubicacion: Number(idUbicacion),
			idlote: Number(idLote),
			idestado: Number(estado),
			cantidad: Number(entrada),
		};

		recepcion.detallesrecepcion.push(filaDatos);
	});

	$.ajax({
		type: 'POST',
		url: '/recepcion/guardarRecepcion',
		contentType: 'application/json',
		data: JSON.stringify(recepcion),
		success: function(resultado) {
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
	    <td>-</td>
	    <td>-</td>
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
		`#autocomplete-ubicacion-${filaCount}`

	);
	buscarLote(
		`#autocomplete-lote-${filaCount}`);
	GetEstado(filaCount);
}

function buscarLote(selector) {
	$(selector)
		.autocomplete({
			source: function(request, response) {
				$.ajax({
					url: `/lote/buscarLotes?termino=${request.term}`,
					type: 'GET',

					success: function(data) {
						console.log("data", data)
						var items = data.map(function(lote) {
							return {
								value: lote.idlote,
								label: lote.lote,
								fechafab: lote.fechafab,
								fechaven: lote.fechaven,
							};
						});
						response(items);
					},
					error: function() {
						response([]);
					},
				});
			},
			select: function(event, ui) {
				let lote = ui.item;
				let input = $(this);
				let fila = $(this).closest('tr');

				input.val(lote.label);
				input.attr('data-value', lote.label);
				input.attr('data-id-lote', lote.value);
				fila.find('td:nth-child(7)').text(formatDate(lote.fechafab));
				fila.find('td:nth-child(8)').text( formatDate(lote.fechaven));
				input.trigger('change');
				return false;
			},
		})
		.autocomplete('widget')
		.addClass('autocomplete-results custom-autocomplete-results')
		.css('background-color', '#E9E9E9')
		.css('list-style-type', 'none')
		.css('padding', '5px')
		.on('mouseover', 'li', function() {
			$(this).css('background-color', '#CBE2F3');
		})
		.on('mouseout', 'li', function() {
			$(this).css('background-color', '#E9E9E9');
		});
}

function buscarUbicacion(selector) {
	$(selector)
		.autocomplete({
			source: function(request, response) {
				$.ajax({
					url: `/ubicacion/buscarUbicaciones?termino=${request.term}`,
					type: 'GET',
					success: function(data) {
						console.log('data', data);
						var items = data.map(function(ubicacion) {
							console.log("ubicacion", ubicacion)
							return {
								value: ubicacion.idubicacion,
								label: ubicacion.ubicacion,
							};
						});
						response(items);
					},
					error: function() {
						response([]);
					},
				});
			},
			select: function(event, ui) {
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
		.on('mouseover', 'li', function() {
			$(this).css('background-color', '#CBE2F3');
		})
		.on('mouseout', 'li', function() {
			$(this).css('background-color', '#E9E9E9');
		});
}

function buscarProducto(selector, url) {
	$(selector).autocomplete({
		source: function(request, response) {
			$.ajax({
				url: `/producto/buscarProductos?termino=${request.term}`,
				type: 'GET',
				success: function(data) {
					var items = data.map(function(product) {
						return {
							value: product.idproducto,
							label: product.producto,
							descripcion: product.descripcion
						};
					});
					response(items);
				},
				error: function() {
					response([]);
				},
			});
		},
		select: function(event, ui) {
			var producto = ui.item;
			var input = $(this);
			var fila = input.closest('tr');
			input.val(producto.label);
			input.attr('data-value', producto.label);
			input.attr('data-id-producto', producto.value);
			fila.find(`#producto-${filaCount}`).text(producto.descripcion);
			input.trigger('change');
			return false;
		},
	}).autocomplete('widget')
		.addClass('autocomplete-results custom-autocomplete-results')
		.css({
			'background-color': '#E9E9E9',
			'list-style-type': 'none',
			'padding': '5px'
		})
		.on('mouseover', 'li', function() {
			$(this).css('background-color', '#CBE2F3');
		})
		.on('mouseout', 'li', function() {
			$(this).css('background-color', '#E9E9E9');
		});
}

function GetSucursales(identidad) {
	$.ajax({
		type: 'GET',
		url: `/sucursal/listarSucursalesPorEntidad?identidad=${identidad}`,
		dataType: 'json',
		success: function(resultado) {
			if (resultado.length > 0) {
				$.each(resultado, function(index, value) {
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
		url: '/estado/listarEstados',
		dataType: 'json',
		success: function(resultado) {
			if (resultado.length > 0) {
				var selectElement = $(`#cboEstado-${filaCount}`);
				selectElement.empty();
				$.each(resultado, function(index, value) {
					selectElement.append(
						`<option value='${value.idestado}'>${value.nomstado}</option>`
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
		success: function(resultado) {
			console.clear('re => ', resultado);
			if (resultado.length > 0) {
				$.each(resultado, function(index, value) {
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
		success: function(resultado) {
			console.clear('re => ', resultado);
			if (resultado.length > 0) {
				$.each(resultado, function(index, value) {
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


function GetOrdenRecepcion() {
	$.ajax({
		url: `/lote/buscarLotes?termino=${request.term}`,
		type: 'GET',

		success: function(data) {
			var items = data.map(function(lote) {
				return {
					value: lote.idlote,
					label: lote.lote,
					fechafab: lote.fechafab,
					fechaven: lote.fechaven,
				};
			});
			response(items);
		},
		error: function() {
			response([]);
		},
	});
}


function buscarOrdenRecepcion() {
	$(selector)
		.autocomplete({
			source: function(request, response) {
				$.ajax({
					url: `/lote/buscarLotes?termino=${request.term}`,
					type: 'GET',

					success: function(data) {
						console.log("data", data)
						var items = data.map(function(lote) {
							return {
								value: lote.idlote,
								label: lote.lote,
								fechafab: lote.fechafab,
								fechaven: lote.fechaven,
							};
						});
						response(items);
					},
					error: function() {
						response([]);
					},
				});
			},
			select: function(event, ui) {
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
		.on('mouseover', 'li', function() {
			$(this).css('background-color', '#CBE2F3');
		})
		.on('mouseout', 'li', function() {
			$(this).css('background-color', '#E9E9E9');
		});
}


function formatDate(fecha) {
    var fechaObj = new Date(fecha);
    var dia = fechaObj.getUTCDate();
    var mes = fechaObj.getUTCMonth() + 1;
    var anio = fechaObj.getUTCFullYear();

    // Asegurarse de que los valores de día y mes tengan dos dígitos
    dia = dia < 10 ? "0" + dia : dia;
    mes = mes < 10 ? "0" + mes : mes;

    return dia + "/" + mes + "/" + anio;
  }
