$(document).on("click", "#btnagregar", function(){
	$("#txtcodigo").val("");
	$("#txtproducto").val("");
	$("#txtdescripcion").val("");
	$("#txtenvase").val("");
	$("#txtpeso").val("");
	$("#hddidregistroproducto").val("0");
 	$("#cbounidad").empty();
	$.ajax({
		type: "GET",
		url: "/unidad/listarUnidades",
		dataType: "json",
		success: function(resultado){
			if(resultado.length > 0){
				$.each(resultado, function(index, value){
					$("#cbounidad").append(
							`<option value="${value.idunidad}">
								${value.descripcion}</option>`
							);
				})
			}			
		}
	})
	$("#modalProducto").modal("show");
});

$(document).on("click", ".btnactualizarproducto", function(){
	$("#txtproducto").val($(this).attr("data-producto"));
	$("#txtdescripcion").val($(this).attr("data-descripcion"));
	$("#txtenvase").val($(this).attr("data-envase"));
	$("#txtpeso").val($(this).attr("data-peso"));
	$("#hddidregistroproducto").val($(this).attr("data-idproducto"));
	$("#cbounidad").empty();
	var idunidad = $(this).attr("data-idunidad");
	$.ajax({
		type: "GET",
		url: "/unidad/listarUnidades",
		dataType: "json",
		success: function(resultado){
			if(resultado.length > 0){
				$.each(resultado, function(index, value){
					$("#cbounidad").append(
							`<option value="${value.idunidad}">
								${value.descripcion}</option>`
							);
				})
				$("#cbounidad").val(idunidad);
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
			producto: $("#txtproducto").val(),
			descripcion: $("#txtdescripcion").val(),
			envase: $("#txtenvase").val(),
			peso: $("#txtpeso").val(),
			idunidad: $("#cbounidad").val()
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
			$(this).attr("data-idproducto")+"?");
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
						"<td>"+value.producto+"</td>"+
						"<td>"+value.descripcion+"</td>"+
						"<td>"+value.unidad.descripcion+"</td>"+
						"<td>"+value.envase+"</td>"+
						"<td>"+value.peso+"</td>"+
						"<td>"+
							"<button type='button' class='btn btn-success btnactualizarproducto'"+
							" data-idproducto='"+value.idproducto+"'"+
							" data-producto='"+value.producto+"'"+	
							" data-descripcion='"+value.descripcion+"'"+
							" data-envase='"+value.envase+"'"+
							" data-peso='"+value.peso+"'"+
							" data-idunidad='"+value.unidad.idunidad+"'"+
							"><i class='fas fa-pen'></i></button></td>"+
						"<td>"+
							"<button type='button' class='btn btn-danger btneliminarproducto'"+	
							" data-idproducto='"+value.idproducto+"'"+
							" data-producto='"+value.producto+"'"+
							"><i class='fas fa-trash'></i></button></td>"+							
						"</tr>")
			})				
		}
	})
}