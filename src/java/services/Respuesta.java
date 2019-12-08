package services;

public class Respuesta {

    private boolean esOk;
    private String mensaje;

    public Respuesta() {
        esOk = false;
    }

    public Respuesta(boolean esOk, String mensaje) {
        this.esOk = esOk;
        this.mensaje = mensaje;
    }

    public boolean isEsOk() {
        return esOk;
    }

    public void setEsOk(boolean esOk) {
        this.esOk = esOk;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}