package dao;

import dal.Conexion;
import dto.Video;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class VideoDaoMySQL extends VideoDao {

    @Override
    public int insert(Video obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tbl_video (titulo, descripcion, videoCode, usuarioId, youtubeVideoID, fechaSubida) VALUES (");
        query.append("'" + obj.getTitulo() + "',");
        query.append("'" + obj.getDescripcion() + "',");
        query.append("'" + obj.getVideoCode() + "',");
        query.append("'" + obj.getUsuarioId() + "',");
        query.append("'" + obj.getYoutubeVideoID() + "',");
        query.append("'" + obj.getFechaSubida() + "'");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Video obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tbl_video SET ");
        query.append("titulo = '" + obj.getTitulo() + "',");
        query.append("descripcion = '" + obj.getDescripcion() + "',");
        query.append("videoCode = '" + obj.getVideoCode() + "',");
        query.append("usuarioId = " + obj.getUsuarioId() + ",");
        query.append("youtubeVideoID = '" + obj.getYoutubeVideoID() + "',");
        query.append("fechaSubida = '" + obj.getFechaSubida() + "' ");
        query.append("WHERE videoId = " + obj.getVideoId());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuffer query = new StringBuffer("DELETE FROM tbl_video ");
        query.append("WHERE videoId = " + id);
        objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public Video get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_video WHERE videoId = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Video obj = new Video();
                int _videoId = objResultSet.getInt("videoId");
                obj.setVideoId(_videoId);

                String _titulo = objResultSet.getString("titulo");
                obj.setTitulo(_titulo);

                String _descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_descripcion);

                String _videoCode = objResultSet.getString("videoCode");
                obj.setVideoCode(_videoCode);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _youtubeVideoID = objResultSet.getString("youtubeVideoID");
                obj.setYoutubeVideoID(_youtubeVideoID);

                String _fechaSubida = objResultSet.getString("fechaSubida");
                obj.setFechaSubida(_fechaSubida);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public ArrayList<Video> getList() {
        ArrayList<Video> registros = new ArrayList<Video>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_video";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Video obj = new Video();
                int _videoId = objResultSet.getInt("videoId");
                obj.setVideoId(_videoId);

                String _titulo = objResultSet.getString("titulo");
                obj.setTitulo(_titulo);

                String _descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_descripcion);

                String _videoCode = objResultSet.getString("videoCode");
                obj.setVideoCode(_videoCode);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _youtubeVideoID = objResultSet.getString("youtubeVideoID");
                obj.setYoutubeVideoID(_youtubeVideoID);

                String _fechaSubida = objResultSet.getString("fechaSubida");
                obj.setFechaSubida(_fechaSubida);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<Video> getVideosByUserId(int userId) {
        ArrayList<Video> registros = new ArrayList<>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_video WHERE usuarioId = " + userId;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Video obj = new Video();
                int _videoId = objResultSet.getInt("videoId");
                obj.setVideoId(_videoId);

                String _titulo = objResultSet.getString("titulo");
                obj.setTitulo(_titulo);

                String _descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_descripcion);

                String _videoCode = objResultSet.getString("videoCode");
                obj.setVideoCode(_videoCode);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _youtubeVideoID = objResultSet.getString("youtubeVideoID");
                obj.setYoutubeVideoID(_youtubeVideoID);

                String _fechaSubida = objResultSet.getString("fechaSubida");
                obj.setFechaSubida(_fechaSubida);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

}
