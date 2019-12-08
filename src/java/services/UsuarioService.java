package services;

import dao.UsuarioDao;
import dto.Usuario;
import factory.FactoryDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Ruta principal: http://localhost:8080/PlataformaWeb/
// Ruta de la api: http://localhost:8080/PlataformaWeb/api/
// Ruta para la api de usuarios: http://localhost:8080/PlataformaWeb/api/usuario
@Path("usuario")
public class UsuarioService {

    // api/usuario/login
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON) // lo que va a devolver
    @Consumes(MediaType.APPLICATION_JSON) // lo que va a recibir
    public Respuesta login(Usuario param) {
        Respuesta respuesta = new Respuesta();
        
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            UsuarioDao dao = factory.getNewUsuarioDao();
            
            Usuario objUsuario = dao.getByUserName(param.getUserName());
            
            if (objUsuario != null
                    && objUsuario.getPassword().equals(param.getPassword())) {
                respuesta.setEsOk(true);
                respuesta.setMensaje(Integer.toString(objUsuario.getUsuarioId()));
            } else {
                respuesta.setMensaje("usuario y/o contraseña incorrectos");
            }
            
        } catch (Exception e) {
            respuesta.setMensaje("Error de autenticacion");
        }
        
        return respuesta;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarse(Usuario param) {
        Respuesta respuesta = new Respuesta();
        
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            UsuarioDao dao = factory.getNewUsuarioDao();
            
            Usuario objUsuario = dao.getByUserName(param.getUserName());
            if (objUsuario != null) {
                respuesta.setMensaje("el username ya se encuentra registrado");
                return respuesta;
            }
            
            int idGenerado = dao.insert(param);
            
            param.setUsuarioId(idGenerado);
            
            respuesta.setEsOk(true);
            respuesta.setMensaje(Integer.toString(idGenerado));
        } catch (Exception e) {
            respuesta.setMensaje("Error de autenticacion");
        }
        
        return respuesta;
    }
    
    @Path("/userOfVideo/{videoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUserOfVideo(@PathParam("videoId") int videoId) {
        try {
            FactoryDao factory = FactoryDao.getFactoryInstance();
            UsuarioDao dao = factory.getNewUsuarioDao();
            
            return dao.getByVideo(videoId);
        } catch (Exception e) {
        }
        
        return null;
    }
    
}