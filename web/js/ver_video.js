$(document).ready(function () {
//    if (!sessionStorage.getItem("usuarioId")) {
//        $("#btn-comentario").prop("disabled", true);
//    } else if (sessionStorage.getItem("usuarioId")) {
//        $("#btn-comentario").prop("disabled", false);
//    }
    
    $("#btn-comentario").click(function() {
        if (sessionStorage.getItem("usuarioId")) {
            comentar();
        } else {
            alert("Inicia sesion para comentar");
        }
    });

    cargarVideo();
    cargarComentarios();
    cargarUsuario();
});

function cargarVideo() {
    var videoId = getQueryVariable("videoId");

    $.ajax({
        url: "api/video/" + videoId,
        method: "GET",
        dataType: "json",
        success: function (data) {
            if (!data) {
                alert("error al cargar el video");
                return;
            }

            var htmlTemplate = $("#ver-video-template").html();
            var fnTemplate = doT.template(htmlTemplate);

            var fila = $("<div></div>");

            var html = fnTemplate(data);
            fila.append(html);

            $("#video").append(fila);
        }
    });
}

function cargarComentarios() {
    var videoId = getQueryVariable("videoId");

    $.ajax({
        url: "api/comentario/" + videoId,
        method: "GET",
        dataType: "json",
        success: function (data) {
            if (!data) {
                alert("error al cargar los comentarios");
                return;
            }

            $("#comentarios").html("");
            var htmlTemplate = $("#comentarios-template").html();
            var fnTemplate = doT.template(htmlTemplate);

            var fila = $("<li></li>");
            var contador = 0;

            for (var i = 0; i < data.length; i++) {
                var obj = data[i];

                contador++;
                var html = fnTemplate(obj);
                fila.append(html);

                if (contador === 1) {
                    $("#comentarios").append(fila);

                    fila = $("<li></li>");
                    contador = 0;
                }
            }

            if (contador !== 0)
                $("#comentarios").append(fila);
        }
    });
}

function cargarUsuario() {
    var videoId = getQueryVariable("videoId");

    $.ajax({
        url: "api/usuario/userOfVideo/" + videoId,
        method: "GET",
        dataType: "json",
        success: function (data) {
            if (!data) {
                alert("hubo un error al cargar la informacion del usuario del video");
                return;
            }
            
            var htmlTemplate = $("#autor-template").html();
            var fnTemplate = doT.template(htmlTemplate);

            var fila = $("<div></div>");

            var html = fnTemplate(data);
            fila.append(html);

            $("#autor").append(fila);
        }
    });
}

function verAutor(usuarioId) {
    window.location.href = "ver_perfil.html?usuarioId=" + usuarioId;
}

function comentar() {
    var videoId = getQueryVariable("videoId");
    var usuarioId = sessionStorage.getItem("usuarioId");
    var comentario = $.trim($("#comentario").val());
    
    if (comentario === "") {
        return;
    }
    
    var param = {
        videoId: videoId,
        usuarioId: usuarioId,
        comentario: comentario,
        fecha: new Date().toMysqlFormat()
    };
    
    $.ajax({
        url: "api/comentario/",
        method: "POST",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function(data) {
            if (!data.esOk) {
                alert(data.mensaje);
                return;
            }
            
            cargarComentarios();
        }
    });
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

/**
 * You first need to create a formatting function to pad numbers to two digits…
 **/
function twoDigits(d) {
    if(0 <= d && d < 10) return "0" + d.toString();
    if(-10 < d && d < 0) return "-0" + (-1*d).toString();
    return d.toString();
}

/**
 * …and then create the method to output the date string as desired.
 * Some people hate using prototypes this way, but if you are going
 * to apply this to more than one Date object, having it as a prototype
 * makes sense.
 **/
Date.prototype.toMysqlFormat = function() {
    return this.getFullYear() + "-" + twoDigits(1 + this.getMonth()) + "-"
            + twoDigits(this.getDate() + " " + twoDigits(this.getHours()) + ":"
            + twoDigits(this.getMinutes()));
};
