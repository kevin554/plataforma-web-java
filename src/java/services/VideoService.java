package services;

import dao.VideoDao;
import dto.Video;
import factory.FactoryDao;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("video")
public class VideoService {

    // POST -> crear datos
    // GET -> consultar datos
    // PUT -> actualizar datos
    // DELETE -> eliminar datos
    // video/
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Video> getVideos() {
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            VideoDao dao = factory.getNewVideoDao();

            return dao.getList();
        } catch (Exception e) {
        }

        return null;
    }
    
    @Path("/{videoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Video getVideo(@PathParam("videoId") int videoId) {
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            VideoDao dao = factory.getNewVideoDao();
            
            return dao.get(videoId);
        } catch (Exception e) {
        }
        
        return null;
    }

    @Path("/videosBy/{usuarioId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Video> getVideosByUser(@PathParam("usuarioId") int userId) {
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            VideoDao dao = factory.getNewVideoDao();

            return dao.getVideosByUserId(userId);
        } catch (Exception e) {
        }

        return null;
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta subir(Video param) {
        Respuesta respuesta = new Respuesta();

        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            VideoDao dao = factory.getNewVideoDao();

            int idGenerado = dao.insert(param);

            respuesta.setEsOk(true);
            respuesta.setMensaje(Integer.toString(idGenerado));
        } catch (Exception e) {
            respuesta.setMensaje("Error al subir video");
        }

        return respuesta;
    }

    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editar(Video param) {
        Respuesta respuesta = new Respuesta();
        
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            VideoDao dao = factory.getNewVideoDao();
            
            dao.update(param);
            
            respuesta.setEsOk(true);
        } catch (Exception e) {
            respuesta.setMensaje("Hubo un error al editar el video");
        }
        
        return respuesta;
    }
    
    @Path("/{videoId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@PathParam("videoId") int videoId) {
        Respuesta respuesta = new Respuesta();
        
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            VideoDao dao = factory.getNewVideoDao();
            
            dao.delete(videoId);
            
            respuesta.setEsOk(true);
        } catch (Exception e) {
            respuesta.setMensaje("error al eliminar");
        }
        
        return respuesta;
    }
    
}
