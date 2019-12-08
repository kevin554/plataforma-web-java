$(document).ready(function () {
    cargarVideos();
});

function cargarVideos() {
    var usuarioId = getQueryVariable("usuarioId");
    
    $.ajax({
        url: "api/video/videosBy/" + usuarioId,
        method: "GET",
        dataType: "json",
        success: function(data) {
            if (!data) {
                alert("hubo un error al cargar los videos");
                return;
            }
            
            var htmlTemplate = $("#videos-template").html();
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

/**
 * Usage
 * Calling getQueryVariable("id") - would return "1".
 * Calling getQueryVariable("image") - would return "awesome.jpg".
 * @param {type} variable
 * @return {getQueryVariable.pair, Boolean}
 */
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }

    return(false);
}
