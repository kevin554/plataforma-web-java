$(document).ready(function() {
    $("#btn-registrar").click(function() {
        var nombreCompleto = $("#nombre").val();
        var userName = $("#username").val();
        var password = $("#password").val();
        var passwordConfirm = $("#password-confirm").val();
        
        var isValid = true;
        
        if ($.trim(nombreCompleto) == "") {
            alert("debe ingresar su nombre");
            $("#nombre").focus();
            isValid = false;
        }
        
        if ($.trim(userName) == "") {
            alert("debe ingresar su username");
            $("#nombre").focus();
            isValid = false;
        }
        
        if ($.trim(password) == "") {
            alert("debe ingresar su contraseña");
            $("#nombre").focus();
            isValid = false;
        }
        
        if ($.trim(passwordConfirm) == "") {
            alert("debe confirmar su contraseña");
            $("#nombre").focus();
            isValid = false;
        }
        
        if (!(password == passwordConfirm)) {
            alert("las contraseñas deben ser iguales");
            isValid = false;
        }
        
        if (!isValid) {
            return;
        }
        
        var param = {
            nombreCompleto: nombreCompleto,
            userName: userName,
            password: password
        };
        
        $.ajax({
            contentType: 'application/json',
            dataType: "json",
            method: "POST",
            url: "api/usuario/registrar",
            data: JSON.stringify(param),
            success: function(data) {
                if (!data.esOk) {
                    alert(data.mensaje);
                    return false;
                }
                
                sessionStorage.setItem("usuarioId", data.mensaje);
                window.location.href = "index.html";
            }
        });
        
        return false;
    }); 
});
