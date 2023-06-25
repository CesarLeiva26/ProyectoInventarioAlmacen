$(document).on("click", "#mostrarmodalguardar", function() {
	$("#hdidusuario").val("0");
	$("#nombre").val("");
	$("#apellido").val("");
	$("#usuario").val("");
	$("#correo").val("");
	$("#telefono").val("");
	$("#idrol").empty();
	$.ajax({
		type: "GET",
		url: "/rol/listarol",
		dataType: "json",
		success: function(resultado) {

			if (resultado.length > 0) {
				$.each(resultado, function(index, value) {
					$("#idrol").append(
							`<option value="${value.idrol}">
					${value.nombrerol}</option>`
					);
				})

			}

		}
	})


	$("#modalregistrousuario").modal("show");
});
//holi

$(document).on("click", "#guardarusuario", function() {
	if($("#idrol").val()==""){
		$("#errorrol").text("Seleccione un rol")
	}else{
		$("#errorrol").text("");
		
	}
	if($("#nombre").val()==""){
		$("#errornombre").text("El campo nombre no puede ser vacio")
	}else{
		$("#errornombre").text("");
	}
	if($("#apellido").val()==""){
		$("#errorapellido").text("Elcampo apellido no puede ser vacio")
	}else{
		$("#errorapellido").text("")
	}
	if($("#usuario").val()==""){
		$("#errorusuario").text("El campo usuario no puede ser vacio")
		
	}else{
		$("#errorusuario").text("");
	}
	if($("#correo").val()==""){
		$("#errorcorreo").text("El campo correo no puede ser vacio")
	}else{
		$("#errorcorreo").text("");
	}
	if($("#telefono").val()==""){
		$("#errortelefono").text("El camo telefono no puede ser vacio")
		
	}else{
		$("#errortelefono").text("");
	}
	
	if($("#nombre").val() != ""
	&& $("#apellido").val()!=""
	||$("#idrol").va()!=empty()
	)
	
	{
	$.ajax({
		type: "POST",
		url: "/usuario/guardarusuario",
		contentType: "application/json",
		data: JSON.stringify({
			idusuario: $("#hdidusuario").val(),
			idrol: $("#idrol").val(),
			nombre: $("#nombre").val(),
			apellidos: $("#apellido").val(),
			usuario: $("#usuario").val(),
			correo: $("#correo").val(),
			telefono: $("#telefono").val()

		}), success: function(resultado) {
			alert(resultado.mensaje);
			listarusandoajax()
		}

	})

	$("#modalregistrousuario").modal("hide");	
		
		
	}
	
	
	
})



$(document).on("click", ".btnactualizar", function() {
	$("#hdidusuario").val($(this).attr("data-idusuario"))
	$("#nombre").val($(this).attr("data-nombre"));
	$("#apellido").val($(this).attr("data-apellidos"));
	$("#usuario").val($(this).attr("data-usuario"));
	$("#correo").val($(this).attr("data-correo"));
	$("#telefono").val($(this).attr("data-telefono"));
	$("#idrol").empty();
	var idrol = $(this).attr("data-idrol");
	$.ajax({
		type: "GET",
		url: "/rol/listarol",
		dataType: "json",
		success: function(resultado) {
			if (resultado.length > 0) {
				$.each(resultado, function(index, value) {
					$("#idrol").append(
						`<option value="${value.idrol}">
						${value.nombrerol}</option>`

					)
				}

				)
				$("#idrol").val(idrol);

			}
		}

	})

	$("#modalregistrousuario").modal("show");
});


$(document).on("click", ".btneliminarpregunta ", function(){
	$("#hddideliminarusuario").val("");
	$("#hddideliminarusuario").val($(this).attr("data-idusuario"));
	$("#mensajeeliminar").text("¿Seguro de Eliminar el "+ 
			$(this).attr("data-idusuario")+"?");
	$("#modalEliminarusuariopreguntadeconfirmacio").modal("show");
})

$(document).on("click", "#confirmareliminar", function(){
	$.ajax({
		type: "DELETE",
		contentType: 'application/json',
		url: "/usuario/eliminarusuario",
		data: JSON.stringify({
		idusuario: $("#hddideliminarusuario").val()
		}),
		success: function(resultado){
			alert(resultado.mensaje);
			listarusandoajax();
		}
	})
	$("#modalEliminarusuariopreguntadeconfirmacio").modal("hide");
})


function listarusandoajax() {
	$.ajax({
		type: "GET",
		url: "/usuario/listausuarioconajax",
		dataType: "json",
		success: function(resultado) {
			$("#tbllistausuarios > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#tbllistausuarios > tbody").append("<tr>" +
					"<td>" + value.idusuario + "</td>" +
					"<td>" + value.rol.nombrerol + "</td>" +
					"<td>" + value.nombre + "</td>" +
					"<td>" + value.apellidos + "</td>" +
					"<td>" + value.usuario + "</td>" +
					"<td>" + value.correo + "</td>" +
					"<td>" + value.telefono + "</td>" +
					"<td>" +
						"<button type='button'  class='btn btn-success btnactualizar'"+
						"data-idusuario='"+value.idusuario+"'"+
						"data-idrol='"+value.rol.idrol+"'"+
						"data-nombre='"+value.nombre+"'"+
						"data-apellidos='"+value.apellidos+"'"+
						"data-usuario='"+value.usuario+"'"+
						"data-correo='"+value.correo+"'"+
						"data-telefono='"+value.telefono+"'"+
						"><i class='fas fa-pencil-alt'></i> </button>"+
					"</td>"+
					"<td>"+
						"<button type='button' class='btn btn-danger btneliminarpregunta'"+	
						" data-idusuario='"+value.idusuario+"'"+
						" data-nombre='"+value.nombre+"'"+
						"><i class='fas fa-trash'></i></button>"+
					"</td>"+		
					"</tr>");
			});
		}
	});
}
















