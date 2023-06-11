$(document).on("click", "#btnagregar", function(){
	$("#txtnombre").val("");
	$("#txtdescripcion").val("");
	$("#hddidregistroproducto").val("0");
	$("#cbopallet").empty();
	$.ajax({
		type: "GET",
		url: "/pallet/listarPallets",
		dataType: "json",
		success: function(resultado){
			if(resultado.length > 0){
				$.each(resultado, function(index, value){
					$("#cbopallet").append(
							`<option value="${value.idpallet}">
								${value.descripcion}</option>`
							);
				})
			}			
		}
	})
	$("#modalProducto").modal("show");
});

$(document).on("click", ".btnactualizarproducto", function(){
	$("#txtnombre").val($(this).attr("data-nombre"));
	$("#txtdescripcion").val($(this).attr("data-descripcion"));
	$("#hddidregistroproducto").val($(this).attr("data-idproducto"));
	$("#cbopallet").empty();
	var idpallet = $(this).attr("data-idpallet");
	$.ajax({
		type: "GET",
		url: "/pallet/listarPallets",
		dataType: "json",
		success: function(resultado){
			if(resultado.length > 0){
				$.each(resultado, function(index, value){
					$("#cbopallet").append(
							`<option value="${value.idpallet}">
								${value.descripcion}</option>`
							);
				})
				$("#cbopallet").val(idpallet);
			}			
		}
	})
	$("#modalProducto").modal("show");
});

$(document).on("click", "#btnguardar", function(){
	$.ajax({
		type: "POST",
		url: "/producto/registrarProducto",
		contentType: "application/json",
		data: JSON.stringify({
			idproducto: $("#hddidregistroproducto").val(),
			nombre: $("#txtnombre").val(),
			descripcion: $("#txtdescripcion").val(),
			idpallet: $("#cbopallet").val()
		}),
		success: function(resultado){
			alert(resultado.mensaje);
			ListarProducto();
		}
	});
	$("#modalProducto").modal("hide");
})

$(document).on("click", ".btneliminarproducto", function(){
	$("#hddideliminarproducto").val("");
	$("#hddideliminarproducto").val($(this).attr("data-idproducto"));
	$("#mensajeeliminar").text("¿Seguro de Eliminar el "+ 
			$(this).attr("data-nombre")+"?");
	$("#modalEliminarProducto").modal("show");
})

$(document).on("click", "#btneliminar", function(){
	$.ajax({
		type: "DELETE",
		contentType: 'application/json',
		url: "/producto/eliminarProducto",
		data: JSON.stringify({
		idproducto: $("#hddideliminarproducto").val()
		}),
		success: function(resultado){
			alert(resultado.mensaje);
			ListarProducto();
		}
	})
	$("#modalEliminarProducto").modal("hide");
})

function ListarProducto(){
	$.ajax({
		type: "GET",
		url: "/producto/listarProductos",
		dataType: "json",
		success: function(resultado){
			$("#tblproducto > tbody").html("");
			$.each(resultado, function(index, value){
				$("#tblproducto > tbody").append("<tr>"+
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
						"</tr>")
			})				
		}
	})
}