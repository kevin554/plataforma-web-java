package dao;

import dal.Conexion;
import dto.Comentario;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class ComentarioDaoMySQL extends ComentarioDao {

    @Override
    public int insert(Comentario obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tbl_comentario (videoId,usuarioId,comentario,fecha) VALUES (");
        query.append(obj.getVideoId() + ",");
        query.append(obj.getUsuarioId() + ",");
        query.append("'" + obj.getComentario() + "',");
        query.append("'" + obj.getFecha() + "' ");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Comentario obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tbl_comentario SET ");
        query.append("videoId = " + obj.getVideoId() + ",");
        query.append("usuarioId = " + obj.getUsuarioId() + ",");
        query.append("comentario = '" + obj.getComentario() + "',");
        query.append("fecha = '" + obj.getFecha() + "' ");
        query.append("WHERE comentarioId = " + obj.getComentarioId());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuffer query = new StringBuffer("DELETE FROM tbl_comentario ");
        query.append("WHERE comentarioId = " + id);
        objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public Comentario get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_comentario WHERE comentarioId = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Comentario obj = new Comentario();
                int _comentarioId = objResultSet.getInt("comentarioId");
                obj.setComentarioId(_comentarioId);

                int _videoId = objResultSet.getInt("videoId");
                obj.setVideoId(_videoId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _comentario = objResultSet.getString("comentario");
                obj.setComentario(_comentario);

                String _fecha = objResultSet.getString("fecha");
                obj.setFecha(_fecha);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public ArrayList<Comentario> getList() {
        ArrayList<Comentario> registros = new ArrayList<Comentario>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_comentario";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Comentario obj = new Comentario();
                int _comentarioId = objResultSet.getInt("comentarioId");
                obj.setComentarioId(_comentarioId);

                int _videoId = objResultSet.getInt("videoId");
                obj.setVideoId(_videoId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _comentario = objResultSet.getString("comentario");
                obj.setComentario(_comentario);

                String _fecha = objResultSet.getString("fecha");
                obj.setFecha(_fecha);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<Comentario> getListOfVideo(int videoId) {
        ArrayList<Comentario> registros = new ArrayList<Comentario>();
        
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_comentario WHERE videoId = " + videoId;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Comentario obj = new Comentario();
                int _comentarioId = objResultSet.getInt("comentarioId");
                obj.setComentarioId(_comentarioId);

                int _videoId = objResultSet.getInt("videoId");
                obj.setVideoId(_videoId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _comentario = objResultSet.getString("comentario");
                obj.setComentario(_comentario);

                String _fecha = objResultSet.getString("fecha");
                obj.setFecha(_fecha);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

}
