$(document).ready(function() {
  function formatDate(fecha) {
    var fechaObj = new Date(fecha);
    var dia = fechaObj.getUTCDate();
    var mes = fechaObj.getUTCMonth() + 1;
    var anio = fechaObj.getUTCFullYear();
    
    dia = dia < 10 ? "0" + dia : dia;
    mes = mes < 10 ? "0" + mes : mes;

    return dia + "-" + mes + "-" + anio;
  }

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
          listarLotes(); 
          $("#modalLote").modal("hide"); 
        },
        error: function() {
          console.log("Después de la llamada AJAX (error)");
          alert("Error al insertar el lote.");
        }
      });
    }
  });

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

  
  $("#btnbuscar").click(function() {
    var lote = $("#cmblotes").val();

    $("#tblLote tbody tr").hide().filter(function() {
      return $(this).find("td:eq(1)").text() === lote;
    }).show();
  });

  
  listarLotes();
});
