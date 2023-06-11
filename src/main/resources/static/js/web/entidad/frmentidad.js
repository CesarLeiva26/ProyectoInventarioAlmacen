function ListarEntidad() {
	$.ajax({
		type: "GET",
		url: "/entidad/listarEntidades",
		dataType: "json",
		success: function(resultado) {
			$("#tblentidad > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#tblentidad > tbody").append(
					"<tr>" +
					"<td>" + value.identidad + "</td>" +
					"<td>" + value.entidad + "</td>" +
					"<td>" + value.nombreEntidad + "</td>" +
					"<td>" + value.tipo + "</td>" +
					"<td>" + value.direccion + "</td>" +
					"<td>" + value.correo + "</td>" +
					"<td>" + value.telefono + "</td>" +
					"<td>" + value.notas + "</td>" +
					"<td>" +
					"<button type='button' class='btn btn-success btnactualizarentidad'" +
					" data-identidad='" + value.identidad + "'" +
					" data-entidad='" + value.entidad + "'" +
					" data-nombreEntidad='" + value.nombreEntidad + "'" +
					" data-tipo='" + value.tipo + "'" +
					" data-direccion='" + value.direccion + "'" +
					" data-correo='" + value.correo + "'" +
					" data-telefono='" + value.telefono + "'" +
					" data-notas='" + value.notas + "'" +
					"><i class='fas fa-pen'></i></button>" +
					"</td>" +
					"<td>" +
					"<button type='button' class='btn btn-danger btneliminarentidad'" +
					" data-identidad='" + value.identidad + "'" +
					" data-entidad='" + value.entidad + "'" +
					"><i class='fas fa-trash'></i></button>" +
					"</td>" +
					"</tr>"
				);
			});
		}
	});
}


$(document).on("click", ".btnactualizarentidad", function(){
	$("#txtentidad").val($(this).attr("data-entidad"));
	$("#txtnombreentidad").val($(this).attr("data-nombreentidad"));
	$("#txttipo").val($(this).attr("data-tipo"));
	$("#txtdireccion").val($(this).attr("data-direccion"));
	$("#txtcorreo").val($(this).attr("data-correo"));
	$("#txttelefono").val($(this).attr("data-telefono"));
	$("#txtnotas").val($(this).attr("data-notas"));
	$("#hddidregistroentidad").val($(this).attr("data-identidad"));
	
	$("#modalEntidad").modal("show");
});


$(document).on("click", "#btnagregar", function(){
	$("#txtidentidad").val("");
	$("#txtentidad").val("");
	$("#txtnombreentidad").val("");
	$("#txttipo").val("");
	$("#txtdireccion").val("");
	$("#txtcorreo").val("");
	$("#txttelefono").val("");
	$("#txtnotas").val("");

	$("#modalEntidad").modal("show");
});


$(document).on("click", "#btnguardar", function(){
	$.ajax({
		type: "POST",
		url: "/entidad/registrarEntidad",
		contentType: "application/json",
		data: JSON.stringify({
			identidad: $("#hddidregistroentidad").val(),
			entidad: $("#txtentidad").val(),
			nombreentidad: $("#txtnombreentidad").val(),
			tipo: $("#txttipo").val(),
			direccion: $("#txtdireccion").val(),
			correo: $("#txtcorreo").val(),
			telefono: $("#txttelefono").val(),
			notas: $("#txtnotas").val()
		}),
		success: function(resultado){
			alert(resultado.mensaje);
			ListarEntidad();
		}
	});
	$("#modalEntidad").modal("hide");
});











$(document).on("click", ".btneliminaridentidad", function(){
	$("#hddideliminarentidad").val("");
	$("#hddideliminarentidad").val($(this).attr("data-identidad"));
	$("#mensajeeliminar").text("¿Seguro de eliminar " + $(this).attr("data-entidad") + "?");
	$("#modalEliminarEntidad").modal("show");
});

$(document).on("click", "#btneliminar", function(){
	$.ajax({
		type: "DELETE",
		contentType: 'application/json',
		url: "/entidad/eliminarEntidad",
		data: JSON.stringify({
			identidad: $("#hddideliminarentidad").val()
		}),
		success: function(resultado){
			alert(resultado.mensaje);
			ListarEntidad();
		}
	});
	$("#modalEliminarEntidad").modal("hide");
});


