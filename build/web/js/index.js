$(document).ready(function () {
    if (!sessionStorage.getItem("usuarioId")) {
        $("#iniciar-sesion").show();
        $("#editar-perfil").hide();
        $("#subir-video").hide();
        $("#cerrar-sesion").hide();
    } else if (sessionStorage.getItem("usuarioId")) {
        $("#iniciar-sesion").hide();
        $("#editar-perfil").show();
        $("#subir-video").show();
        $("#cerrar-sesion").show();
    }

    $("#cerrar-sesion").click(function () {
        sessionStorage.removeItem("usuarioId");
        window.location.href = "index.html";
        return;
    });

    cargarVideos();
});

function cargarVideos() {
    $.ajax({
        url: "api/video/",
        method: "GET",
        dataType: "json", // lo que espero recibir
        success: function(data) {
            if (!data) {
                alert("Error al traer los datos");
                return;
            }
            
            var htmlTemplate = $("#video-template").html();
            var fnTemplate = doT.template(htmlTemplate);
            
            var fila = $("<div></div>");
            var contador = 0;
            
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                
                contador++;
                var html = fnTemplate(obj);
                fila.append(html);
                
                if (contador === 1) {
                    $("#videos").append(fila);
                    
                    fila = $("<div></div>");
                    contador = 0;
                }
            }
            
            if (contador !== 0)
                $("#videos").append(fila);
        }
    });
}

function verVideo(videoId) {
    window.location.href = "ver_video.html?videoId=" + videoId;
}
