$(document).on("click", "#btnagregar", function(){
	$("#txtidsucursal").val("");
	$("#txtnomsucursal").val("");
	$("#txtnotas").val("");
	$("#hddidregistrosucursal").val("0");
	
	$("#cboentidad").empty();
	$.ajax({
		type: "GET",
		url: "/entidad/listarEntidades",
		dataType: "json",
		success: function(resultado){
			if(resultado.length > 0){
				$.each(resultado, function(index, value){
					$("#cboentidad").append(
							`<option value="${value.identidad}">
								${value.entidad}</option>`
							);
				})
			}			
		}
	})
	
	$("#modalSucursal").modal("show");
});



$(document).on("click", "#btnguardar", function(){
	$.ajax({
		type: "POST",
		url: "/sucursal/registrarSucursal",
		contentType: "application/json",
		data: JSON.stringify({
			idsucursal: $("#hddidregistrosucursal").val(),
			nomsucursal: $("#txtnomsucursal").val(),
			identidad: $("#cboentidad").val(),
			notas: $("#txtnotas").val()
		}),
		success: function(resultado){
			alert(resultado.mensaje);
			listarSucursal();
		}
	});
	$("#modalSucursal").modal("hide");
})



$(document).on("click", ".btnactualizarsucursal", function(){
	$("#txtnomsucursal").val($(this).attr("data-nomsucursal"));
	$("#txtnotas").val($(this).attr("data-notas"));
	$("#hddidregistrosucursal").val($(this).attr("data-idsucursal"));
	
	$("#cboentidad").empty();
	$.ajax({
		type: "GET",
		url: "/entidad/listarEntidades",
		dataType: "json",
		success: function(resultado){
			if(resultado.length > 0){
				$.each(resultado, function(index, value){
					$("#cboentidad").append(
							`<option value="${value.identidad}">
								${value.entidad}</option>`
							);
				})
			}			
		}
	})
	
	
	$("#modalSucursal").modal("show");
});




function listarSucursal() {
	$.ajax({
		type: "GET",
		url: "/sucursal/listarSucursales",
		dataType: "json",
		success: function(resultado) {
			$("#tblsucursal > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#tblsucursal > tbody").append(
					"<tr>" +
					"<td>" + value.idsucursal + "</td>" +
					"<td>" + value.nomsucursal + "</td>" +
					"<td>" + value.entidad.nombreEntidad + "</td>" +
					"<td>" + value.notas + "</td>" +
					"<td>" +
					"<button type='button' class='btn btn-success btnactualizarsucursal'" +
					" data-idsucursal='" + value.idsucursal + "'" +
					" data-nomsucursal='" + value.nomsucursal + "'" +
					" data-entidad.nombreEntidad='" + value.entidad.nombreEntidad + "'" +
					" data-notas='" + value.notas + "'" +
					"><i class='fas fa-pen'></i></button>" +
					"</td>" +
					"<td>" +
					"<button type='button' class='btn btn-danger btneliminarsucursal'" +
					" data-idsucursal='" + value.idsucursal + "'" +
					" data-nomsucursal='" + value.nomsucursal + "'" +
					"><i class='fas fa-trash'></i></button>" +
					"</td>" +
					"</tr>"
				);
			});
		}
	});
}


$(document).on("click", ".btneliminaridsucursal", function(){
	$("#hddideliminarsucursal").val("");
	$("#hddideliminarsucursal").val($(this).attr("data-idsucursal"));
	$("#mensajeeliminar").text("¿Seguro de Eliminar el "+ 
			$(this).attr("data-idsucursal")+"?");
	$("#modalEliminarSucursal").modal("show");
})



$(document).on("click", "#btneliminar", function(){
	$.ajax({
		type: "DELETE",
		contentType: 'application/json',
		url: "/sucursal/eliminarSucursal",
		data: JSON.stringify({
		idsucursal: $("#hddideliminarsucursal").val()
		}),
		success: function(resultado){
			alert(resultado.mensaje);
			listarSucursal();
		}
	})
	$("#modalEliminarSucursal").modal("hide");
})


