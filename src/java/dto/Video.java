package dto;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class Video {

    private int videoId;
    private String titulo;
    private String descripcion;
    private String videoCode;
    private int usuarioId;
    private String youtubeVideoID;
    private String fechaSubida;

    public Video() {
        ;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoId() {
        return this.videoId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    public String getVideoCode() {
        return this.videoCode;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getUsuarioId() {
        return this.usuarioId;
    }

    public void setYoutubeVideoID(String youtubeVideoID) {
        this.youtubeVideoID = youtubeVideoID;
    }

    public String getYoutubeVideoID() {
        return this.youtubeVideoID;
    }

    public void setFechaSubida(String fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public String getFechaSubida() {
        return this.fechaSubida;
    }

}
