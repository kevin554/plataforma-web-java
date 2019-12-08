package logica;

import dao.ComentarioDao;
import dao.UsuarioDao;
import dao.VideoDao;
import dto.Comentario;
import dto.Usuario;
import dto.Video;
import factory.FactoryDao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDao {

    public static void main(String[] args) {
        FactoryDao factory = FactoryDao.getFactoryInstance();
        UsuarioDao usuarioDAO = factory.getNewUsuarioDao();
        
        Usuario obj = usuarioDAO.getByUserName("tefor");
        
        VideoDao videoDAO = factory.getNewVideoDao();
        Video video = videoDAO.get(21);
        System.out.println(video.getTitulo());
        video.setTitulo("nuevoTituo");
        try {
            videoDAO.update(video);
        } catch (Exception ex) {
            Logger.getLogger(TestDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        ArrayList<Video> list = videoDAO.getVideosByUserId(2);
//        for (Video video : list) {
//            System.out.println();
//        }
        
        ComentarioDao comentarioDao = factory.getNewComentarioDao();
        ArrayList<Comentario> comentarios = comentarioDao.getListOfVideo(9);
        
//        Video video = new Video();
//        video.setTitulo("123");
//        video.setDescripcion("123");
//        video.setVideoCode("123");
//        video.setUsuarioId(2);
//        video.setYoutubeVideoID("123");
//        video.setFechaSubida("20170412");
//        try {
//            int insert = videoDAO.insert(video);
//        } catch (Exception ex) {
//            Logger.getLogger(TestDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        System.out.println();
    }
    
}
