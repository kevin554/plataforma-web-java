package dao;

import dal.Conexion;
import dto.Usuario;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class UsuarioDaoMySQL extends UsuarioDao {

    @Override
    public int insert(Usuario obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tbl_usuario (nombreCompleto,username,password) VALUES (");
        
        query.append("'" + obj.getNombreCompleto() + "',");
        query.append("'" + obj.getUserName() + "',");
        query.append("'" + obj.getPassword() + "' ");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Usuario obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tbl_usuario SET ");
        query.append("nombreCompleto = '" + obj.getNombreCompleto() + "',");
        query.append("userName = '" + obj.getUserName() + "',");
        query.append("password = '" + obj.getPassword() + "' ");
        query.append("WHERE usuarioId = " + obj.getUsuarioId());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuffer query = new StringBuffer("DELETE FROM tbl_usuario ");
        query.append("WHERE usuarioId = " + id);
        objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public Usuario get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_usuario WHERE usuarioId = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Usuario obj = new Usuario();
                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _nombreCompleto = objResultSet.getString("nombreCompleto");
                obj.setNombreCompleto(_nombreCompleto);

                String _userName = objResultSet.getString("userName");
                obj.setUserName(_userName);

                String _password = objResultSet.getString("password");
                obj.setPassword(_password);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> getList() {
        ArrayList<Usuario> registros = new ArrayList<Usuario>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_usuario";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Usuario obj = new Usuario();
                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _nombreCompleto = objResultSet.getString("nombreCompleto");
                obj.setNombreCompleto(_nombreCompleto);

                String _userName = objResultSet.getString("userName");
                obj.setUserName(_userName);

                String _password = objResultSet.getString("password");
                obj.setPassword(_password);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public Usuario getByUserName(String userName) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_usuario WHERE userName = '" + userName + "'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Usuario obj = new Usuario();
                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _nombreCompleto = objResultSet.getString("nombreCompleto");
                obj.setNombreCompleto(_nombreCompleto);

                String _userName = objResultSet.getString("userName");
                obj.setUserName(_userName);

                String _password = objResultSet.getString("password");
                obj.setPassword(_password);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public Usuario getByVideo(int videoId) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbl_usuario WHERE usuarioId = (SELECT usuarioId FROM tbl_video WHERE videoId = " + videoId + ")";
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Usuario obj = new Usuario();
                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _nombreCompleto = objResultSet.getString("nombreCompleto");
                obj.setNombreCompleto(_nombreCompleto);

                String _userName = objResultSet.getString("userName");
                obj.setUserName(_userName);

                String _password = objResultSet.getString("password");
                obj.setPassword(_password);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

}
