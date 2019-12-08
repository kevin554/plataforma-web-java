package dao;

import dto.Comentario;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class ComentarioDao {

    public abstract int insert(Comentario obj) throws Exception;

    public abstract void update(Comentario obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Comentario> getList();

    public abstract Comentario get(int id);
    
    public abstract ArrayList<Comentario> getListOfVideo(int videoId);

}
