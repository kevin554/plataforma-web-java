package services;

import dao.ComentarioDao;
import dto.Comentario;
import factory.FactoryDao;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("comentario")
public class ComentarioService {

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comentario> getComentarios() {
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            ComentarioDao dao = factory.getNewComentarioDao();
            
            return dao.getList();
        } catch (Exception e) {
        }
        
        return null;
    }
    
    @Path("/{videoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comentario> getComentariosOfVideo(@PathParam("videoId") int videoId) {
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            ComentarioDao dao = factory.getNewComentarioDao();
            
            return dao.getListOfVideo(videoId);
        } catch (Exception e) {
        }
        
        return null;
    }
    
    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta comentar(Comentario param) {
        Respuesta respuesta = new Respuesta();
        
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            ComentarioDao dao = factory.getNewComentarioDao();
            
            int idGenerado = dao.insert(param);
            
            respuesta.setEsOk(true);
            respuesta.setMensaje(Integer.toString(idGenerado));
        } catch (Exception e) {
            respuesta.setMensaje("Hubo un error al publicar tu comentario");
        }
        
        return respuesta;
    }

}
