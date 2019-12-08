$(document).ready(function() {
    
    if (sessionStorage.getItem("usuarioId")) {
        window.location.href = "index.html";
    }
    
    $("#btn-login").click(function(){
        var userName = $("#username").val();
        var password = $("#password").val();

        if ($.trim(userName) == "") {
            alert("debe ingresar el nombre de usuario");
            $("#username").focus();
            return false;
        }

        if ($.trim(password) == "") {
            alert("debe ingresar su contraseña");
            $("#password").focus();
            return false;
        }

        var param = {
            userName: userName,
            password: password
        };
        
        $.ajax({
            contentType: 'application/json',
            dataType: "json",
            method: "POST",
            url: "api/usuario/login",
            data: JSON.stringify(param),
            success: function(data) {
                if (!data.esOk) {
                    alert(data.mensaje);
                    return;
                }
                
                sessionStorage.setItem("usuarioId", data.mensaje);
                window.location.href = "index.html";
            }
        });

        return false;
    });
});