$(document).ready(function() {
  // Función para formatear la fecha en formato dd-mm-yyyy
  function formatDate(fecha) {
    var fechaObj = new Date(fecha);
    var dia = fechaObj.getUTCDate();
    var mes = fechaObj.getUTCMonth() + 1;
    var anio = fechaObj.getUTCFullYear();

    // Asegurarse de que los valores de día y mes tengan dos dígitos
    dia = dia < 10 ? "0" + dia : dia;
    mes = mes < 10 ? "0" + mes : mes;

    return dia + "-" + mes + "-" + anio;
  }

  // Función para cargar la lista de lotes en la tabla
  function listarLotes() {
    $.ajax({
      type: "GET",
      url: "/lote/listarLotes",
      dataType: "json",
      success: function(resultado) {
        var $tblLote = $("#tblLote > tbody");
        $tblLote.empty();

        $.each(resultado, function(index, value) {
          var fechaFab = formatDate(value.fechafab);
          var fechaVen = formatDate(value.fechaven);

          // Construir la fila de la tabla utilizando una plantilla literal
          var loteRow = `
            <tr>
              <td>${value.idlote}</td>
              <td>${value.lote}</td>
              <td>${fechaFab}</td>
              <td>${fechaVen}</td>
              <td>
                <button type="button" class="btn btn-danger btneliminarlote"
                  data-idlote="${value.idlote}"
                  data-lote="${value.lote}">
                  <i class="fas fa-trash"></i>
                </button>
              </td>
            </tr>`;

          $tblLote.append(loteRow);
        });

        bindDeleteButtonEvents();
      }
    });
  }

  // Función para asignar eventos a los botones de eliminar lote
  function bindDeleteButtonEvents() {
    $(".btneliminarlote").off("click");

    $(".btneliminarlote").on("click", function() {
      var entityId = $(this).attr("data-idlote");
      var entityName = $(this).attr("data-lote");

      $("#hddideliminarlote").val(entityId);
      $("#mensajeeliminar").text("¿Seguro de eliminar " + entityName + "?");
      $("#modalEliminarLote").modal("show");
    });

    $("#btneliminar").off("click");

    $("#btneliminar").on("click", function() {
      // Enviar solicitud AJAX para eliminar el lote
      $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/lote/eliminarLote",
        data: JSON.stringify({
          idlote: $("#hddideliminarlote").val()
        }),
        success: function(resultado) {
          listarLotes();
        }
      });

      $("#modalEliminarLote").modal("hide");
    });
  }

  // Evento de clic en el botón "Guardar" del formulario de lote
  $("#btnguardarlote").click(function() {
    var $txtLote = $("#txtlote");
    var $txtFechaFab = $("#txtfechafab");
    var $errorLote = $("#errorlote");
    var $errorFechaFab = $("#errorfechafab");

    var lote = $txtLote.val().trim();
    var fechaFab = $txtFechaFab.val().trim();
    var error = false;

    if (lote === "") {
      $errorLote.text("El campo Lote es obligatorio.");
      error = true;
    } else {
      $errorLote.text("");
    }

    if (fechaFab === "") {
      $errorFechaFab.text("El campo Fecha de Fabricación es obligatorio.");
      error = true;
    } else {
      $errorFechaFab.text("");
    }

    if (!error) {
      // Enviar solicitud AJAX para registrar el lote
      $.ajax({
        type: "POST",
        url: "/lote/registrarLote",
        contentType: "application/json",
        data: JSON.stringify({
          lote: lote,
          fechafab: fechaFab
        }),
        success: function(resultado) {
          alert(resultado.mensaje);
          listarLotes(); // Actualizar la lista de lotes después de la inserción
          $("#modalLote").modal("hide"); // Cerrar el modal
        },
        error: function() {
          console.log("Después de la llamada AJAX (error)");
          alert("Error al insertar el lote.");
        }
      });
    }
  });

  // Cargar los lotes en el combobox al cargar la página
  $.ajax({
    type: "GET",
    url: "/lote/listarLotes",
    dataType: "json",
    success: function(data) {
      var cmbLotes = $("#cmblotes");
      $.each(data, function(index, lote) {
        cmbLotes.append("<option value='" + lote.lote + "'>" + lote.lote + "</option>");
      });
    }
  });

  // Filtrar los datos al hacer clic en el botón buscar
  $("#btnbuscar").click(function() {
    var lote = $("#cmblotes").val();

    $("#tblLote tbody tr").hide().filter(function() {
      return $(this).find("td:eq(1)").text() === lote;
    }).show();
  });

  // Llamar a la función listarLotes para cargar los lotes al cargar la página
  listarLotes();
});
