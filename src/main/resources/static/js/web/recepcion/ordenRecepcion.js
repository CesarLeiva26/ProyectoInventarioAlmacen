
$(document).ready(function() {
	listarOrdenRecepcion()
});


function listarOrdenRecepcion() {
	$.ajax({
		type: "GET",
		url: "/ordenrecepcion/listarordenrecepcion",
		dataType: "json",
		success: function(resultado) {
			$("#ordenRecepcion > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#ordenRecepcion > tbody").append("<tr>" +
					"<td>" + value?.idrecepcion + "</td>" +
					"<td>" + value?.tipomov + "</td>" +
					"<td>" + value?.fechamov + "</td>" +
					"<td>" + value?.docresp + "</td>" +
					"<td>" + value?.identidad?.entidad + "</td>" +
					"<td>" + value?.notas + "</td>" +
					"</tr>");
			});
		}
	});
}


