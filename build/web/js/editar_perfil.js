$(document).ready(function() {
   
    if (!sessionStorage.getItem("usuarioId")) {
        window.location.href = "index.html";
        return false;
    }
    
    cargarVideos();
});

function cargarVideos() {
    $.ajax({
        url: "api/video/videosBy/" + sessionStorage.getItem("usuarioId"),
        dataType: "json", // lo que recibo
        method: "GET",
        success: function(data) {
            if (!data) {
                alert("Error al traer datos");
                return;
            }
            
            $("#editar-videos").html("");
            var htmlTemplate = $("#editar-videos-usuario-template").html();
            var fnTemplate = doT.template(htmlTemplate);
            
            var fila = $("<li></li>");
            var contador = 0;
            
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                
                contador++;
                var html = fnTemplate(obj);
                fila.append(html);
                
                if (contador === 1) {
                    $("#editar-videos").append(fila);
                    
                    fila = $("<li></li>");
                    contador = 0;
                }
            }
            
            if (contador !== 0)
                $("#editar-videos").append(fila);
        }
    });
}

function editarVideo(videoId) {
    window.location.href = "subir_video.html?videoId=" + videoId;
}

function eliminarVideo(videoId) {
    $.ajax({
        url: "api/video/" + videoId,
        dataType: "json", // lo que espero recibir
        method: "DELETE",
        success: function(data) {
            if (!data) {
                alert("error al eliminar video");
                return;
            }
            
            if (!data.esOk) {
                alert(data.mensaje);
                return;
            }
            
            cargarVideos();
        }
    });
}
