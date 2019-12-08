package dto;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class Comentario {

    private int comentarioId;
    private int videoId;
    private int usuarioId;
    private String comentario;
    private String fecha;

    public Comentario() {
        ;
    }

    public void setComentarioId(int comentarioId) {
        this.comentarioId = comentarioId;
    }

    public int getComentarioId() {
        return this.comentarioId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoId() {
        return this.videoId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getUsuarioId() {
        return this.usuarioId;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return this.fecha;
    }

}
