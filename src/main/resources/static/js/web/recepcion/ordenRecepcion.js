
$(document).ready(function() {
	
});


function listarOrdenRecepcion() {
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


