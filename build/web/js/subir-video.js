$(document).ready(function () {

    if (!sessionStorage.getItem("usuarioId")) {
        window.location.href = "index.html";
        return false;
    }

    if (getQueryVariable("videoId") != false) {
        cargarDatosAlFormulario();
    }

    $("#btn-subir-video").click(function () {
        var titulo = $("#titulo").val();
        var descripcion = $("#descripcion").val();
        var videoCode = $("#videoCode").val();
        var youtubeVideoID = $("#youtubeVideoID").val();

        if ($.trim(titulo) == "") {
            alert('debe ingresar el titulo del video');
            $("#titulo").focus();
            return;
        }

        if ($.trim(descripcion) == "") {
            alert('debe ingresar la descripcion del video');
            $("#descripcion").focus();
            return;
        }

        if ($.trim(videoCode) == "") {
            alert('debe ingresar el codigo del video');
            $("#videoCode").focus();
            return;
        }

        if ($.trim(youtubeVideoID) == "") {
            alert('debe ingresar el ID del video de youtube');
            $("#youtubeVideoID").focus();
            return;
        }

        // insertar
        if (!getQueryVariable("videoId")) {

            var param = {
                titulo: titulo,
                descripcion: descripcion,
                videoCode: videoCode,
                usuarioId: sessionStorage.getItem("usuarioId"),
                youtubeVideoID: youtubeVideoID,
                fechaSubida: new Date().toMysqlFormat()
            };

            $.ajax({
                contentType: 'application/json',
                dataType: 'json',
                method: 'POST',
                url: 'api/video/',
                data: JSON.stringify(param),
                success: function (data) {
                    if (!data.esOk) {
                        alert(data.mensaje);
                        return false;
                    }

                    window.location.href = "index.html";
                }
            });
        } else { // actualizar
            var param = {
                videoId: getQueryVariable("videoId"),
                titulo: titulo,
                descripcion: descripcion,
                videoCode: videoCode,
                usuarioId: sessionStorage.getItem("usuarioId"),
                youtubeVideoID: youtubeVideoID,
                fechaSubida: new Date().toMysqlFormat()
            };

            $.ajax({
                contentType: 'application/json',
                dataType: 'json',
                method: 'PUT',
                url: 'api/video/',
                data: JSON.stringify(param),
                success: function (data) {
                    if (!data.esOk) {
                        alert(data.mensaje);
                        return false;
                    }

                    window.location.href = "editar_perfil.html";
                }
            });


        }

        return false;
    });
});

function cargarDatosAlFormulario() {
    var videoId = getQueryVariable("videoId");

    $.ajax({
        dataType: 'json',
        method: 'GET',
        url: 'api/video/' + videoId,
        success: function (data) {
            if (!data) {
                alert("hubo un error al editar el video");
                return false;
            }

            $("#titulo").val(data.titulo);
            $("#descripcion").val(data.descripcion);
            $("#videoCode").val(data.videoCode);
            $("#youtubeVideoID").val(data.youtubeVideoID);
        }
    });
}

/**
 * You first need to create a formatting function to pad numbers to two digits…
 **/
function twoDigits(d) {
    if (0 <= d && d < 10)
        return "0" + d.toString();
    if (-10 < d && d < 0)
        return "-0" + (-1 * d).toString();
    return d.toString();
}

/**
 * …and then create the method to output the date string as desired.
 * Some people hate using prototypes this way, but if you are going
 * to apply this to more than one Date object, having it as a prototype
 * makes sense.
 **/
Date.prototype.toMysqlFormat = function () {
    return this.getFullYear() + "-" + twoDigits(1 + this.getMonth()) + "-"
            + twoDigits(this.getDate() + " " + twoDigits(this.getHours()) + ":"
                    + twoDigits(this.getMinutes()));
};

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
