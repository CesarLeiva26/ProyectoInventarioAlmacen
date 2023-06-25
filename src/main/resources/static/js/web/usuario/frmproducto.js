$(document).on("click", "#btnagregar", function(){
	$("#txtnombre").val("");
	$("#txtdescripcion").val("");
	$("#hddidregistroproducto").val("0");
	$("#cbopallet").empty();

	cargarPallets(function(resultado) {
		if (resultado.length > 0) {
			$.each(resultado, function(index, value){
				$("#cbopallet").append(
					`<option value="${value.idpallet}">
						${value.descripcion}</option>`
				);
			});
		}
	});

	$("#modalProducto").modal("show");
});

$(document).on("click", ".btnactualizarproducto", function(){
	$("#txtnombre").val($(this).attr("data-nombre"));
	$("#txtdescripcion").val($(this).attr("data-descripcion"));
	$("#hddidregistroproducto").val($(this).attr("data-idproducto"));
	$("#cbopallet").empty();
	var idpallet = $(this).attr("data-idpallet");

	cargarPallets(function(resultado) {
		if (resultado.length > 0) {
			$.each(resultado, function(index, value){
				$("#cbopallet").append(
					`<option value="${value.idpallet}">
						${value.descripcion}</option>`
				);
			});
			$("#cbopallet").val(idpallet);
		}
	});

	$("#modalProducto").modal("show");
});

$(document).on("click", "#btnguardar", function(){
	var productoData = {
		idproducto: $("#hddidregistroproducto").val(),
		nombre: $("#txtnombre").val(),
		descripcion: $("#txtdescripcion").val(),
		envase: $("#txtenvase").val(),
		peso: $("#txtpeso").val(),
		idunidad: $("#cbounidad").val()
	};

	$.ajax({
		type: "POST",
		url: "/producto/registrarProducto",
		contentType: "application/json",
		data: JSON.stringify(productoData),
		success: function(resultado){
			alert(resultado.mensaje);
			ListarProducto(); // Llamar a la función para actualizar la tabla
		}
	});

	$("#modalProducto").modal("hide");
});

$(document).on("click", ".btneliminarproducto", function(){
	$("#hddideliminarproducto").val($(this).attr("data-idproducto"));
	$("#mensajeeliminar").text("¿Seguro de Eliminar el " +
			$(this).attr("data-nombre") + "?");
	$("#modalEliminarProducto").modal("show");
});

$(document).on("click", "#btneliminar", function(){
	var productoId = $("#hddideliminarproducto").val();

	$.ajax({
		type: "DELETE",
		contentType: 'application/json',
		url: "/producto/eliminarProducto",
		data: JSON.stringify({
			idproducto: productoId
		}),
		success: function(resultado){
			alert(resultado.mensaje);
			ListarProducto();
		},
		error: function(xhr, status, error) {
			alert("Error al eliminar el producto. Por favor, inténtelo de nuevo.");
		}
	});

	$("#modalEliminarProducto").modal("hide");
});

function cargarPallets(callback) {
	$.ajax({
		type: "GET",
		url: "/pallet/listarPallets",
		dataType: "json",
		success: function(resultado){
			callback(resultado);
		}
	});
}

function ListarProducto(){
	$.ajax({
		type: "GET",
		url: "/producto/listarProductos",
		dataType: "json",
		success: function(resultado){
			var tableBody = $("#tblproducto > tbody");
			tableBody.empty();

			if (resultado.length > 0) {
				$.each(resultado, function(index, value){
					var row = "<tr>"+
						"<td>"+value.idproducto+"</td>"+
						"<td>"+value.nombre+"</td>"+
						"<td>"+value.descripcion+"</td>"+
						"<td>"+value.pallet.descripcion+"</td>"+
						"<td>"+
							"<button type='button' class='btn btn-success btnactualizarproducto'"+
							" data-idproducto='"+value.idproducto+"'"+
							" data-nombre='"+value.nombre+"'"+
							" data-descripcion='"+value.descripcion+"'"+
							" data-idpallet='"+value.pallet.idpallet+"'"+
							"><i class='fas fa-pen'></i></button></td>"+
						"<td>"+
							"<button type='button' class='btn btn-danger btneliminarproducto'"+	
							" data-idproducto='"+value.idproducto+"'"+
							" data-nombre='"+value.nombre+"'"+
							"><i class='fas fa-trash'></i></button></td>"+							
						"</tr>";

					tableBody.append(row);
				});
			}
		}
	});
}
