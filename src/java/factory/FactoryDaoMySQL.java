package factory;

import dal.Configuracion;
import dao.*;

/**
 *
 * @author Jose Carlos Gutierrez
 */
class FactoryDaoMySQL extends FactoryDao {

    private FactoryDaoMySQL() {
        ;
    }

    public static FactoryDao getFactoryInstance() {
        if (instancia == null) {
            instancia = new FactoryDaoMySQL();
        }
        return instancia;
    }

    @Override
    public ComentarioDao getNewComentarioDao() {
        return new ComentarioDaoMySQL();
    }

    @Override
    public UsuarioDao getNewUsuarioDao() {
        return new UsuarioDaoMySQL();
    }

    @Override
    public VideoDao getNewVideoDao() {
        return new VideoDaoMySQL();
    }

}
