package dao;

import dto.Video;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class VideoDao {

    public abstract int insert(Video obj) throws Exception;

    public abstract void update(Video obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Video> getList();

    public abstract Video get(int id);

    public abstract ArrayList<Video> getVideosByUserId(int userId);

}
