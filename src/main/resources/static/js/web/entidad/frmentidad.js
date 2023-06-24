function listarEntidades() {
  $.ajax({
    type: "GET",
    url: "/entidad/listarEntidades",
    dataType: "json",
    success: function(resultado) {
      $("#tblentidad > tbody").html("");
      var cmbEntidades = $("#cmbentidades");
      cmbEntidades.empty();

      $.each(resultado, function(index, value) {
        var entidadRow =
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
          " data-nombreentidad='" + value.nombreEntidad + "'" +
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
          "</tr>";

        $("#tblentidad > tbody").append(entidadRow);

        cmbEntidades.append("<option value='" + value.nombreEntidad + "'>" + value.nombreEntidad + "</option>");
      });

      bindDeleteButtonEvents();
    }
  });
}

function bindDeleteButtonEvents() {
  $(".btneliminarentidad").off("click");

  $(".btneliminarentidad").on("click", function() {
    var entityId = $(this).attr("data-identidad");
    var entityName = $(this).attr("data-entidad");

    $("#hddideliminarentidad").val(entityId);
    $("#mensajeeliminar").text("¿Seguro de eliminar " + entityName + "?");
    $("#modalEliminarEntidad").modal("show");
  });

  $("#btneliminar").off("click");

  $("#btneliminar").on("click", function() {
    $.ajax({
      type: "DELETE",
      contentType: "application/json",
      url: "/entidad/eliminarEntidad",
      data: JSON.stringify({
        identidad: $("#hddideliminarentidad").val()
      }),
      success: function(resultado) {
        listarEntidades();
      }
    });
    $("#modalEliminarEntidad").modal("hide");
  });
}

$(document).ready(function() {
  $.ajax({
    type: "GET",
    url: "/entidad/listarEntidades",
    dataType: "json",
    success: function(data) {
      var cmbEntidades = $("#cmbentidades");
      $.each(data, function(index, entidad) {
        cmbEntidades.append("<option value='" + entidad.nombreEntidad + "'>" + entidad.nombreEntidad + "</option>");
      });
    }
  });

  $("#btnbuscar").click(function() {
    var nombre = $("#cmbentidades").val();

    $("#tblentidad tbody tr").hide().filter(function() {
      return $(this).find("td:eq(2)").text() === nombre;
    }).show();
  });

  $(document).on("click", ".btnactualizarentidad", function() {
    var entityId = $(this).attr("data-identidad");
    var entityName = $(this).attr("data-entidad");
    var entityNombreEntidad = $(this).attr("data-nombreentidad");
    var entityTipo = $(this).attr("data-tipo");
    var entityDireccion = $(this).attr("data-direccion");
    var entityCorreo = $(this).attr("data-correo");
    var entityTelefono = $(this).attr("data-telefono");
    var entityNotas = $(this).attr("data-notas");

    $("#txtidentidad").val(entityId);
    $("#txtentidad").val(entityName);
    $("#txtnombreentidad").val(entityNombreEntidad);
    $("#txttipo").val(entityTipo);
    $("#txtdireccion").val(entityDireccion);
    $("#txtcorreo").val(entityCorreo);
    $("#txttelefono").val(entityTelefono);
    $("#txtnotas").val(entityNotas);
    $("#hddidregistroentidad").val(entityId);

    $("#modalEntidad").modal("show");
  });

  $(document).on("click", "#btnagregar", function() {
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

  $(document).on("click", "#btnguardar", function() {
    var entityId = $("#hddidregistroentidad").val();
    var entityName = $("#txtentidad").val();
    var entityNombreEntidad = $("#txtnombreentidad").val();
    var entityTipo = $("#txttipo").val();
    var entityDireccion = $("#txtdireccion").val();
    var entityCorreo = $("#txtcorreo").val();
    var entityTelefono = $("#txttelefono").val();
    var entityNotas = $("#txtnotas").val();

    $.ajax({
      type: "POST",
      url: "/entidad/registrarEntidad",
      contentType: "application/json",
      data: JSON.stringify({
        identidad: entityId,
        entidad: entityName,
        nombreentidad: entityNombreEntidad,
        tipo: entityTipo,
        direccion: entityDireccion,
        correo: entityCorreo,
        telefono: entityTelefono,
        notas: entityNotas
      }),
      success: function(resultado) {
        alert(resultado.mensaje);
        listarEntidades();
      }
    });
    $("#modalEntidad").modal("hide");
  });

  listarEntidades();
});
