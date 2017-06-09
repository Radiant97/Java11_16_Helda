package by.tc.like_it.dao.impl;

import by.tc.like_it.dao.InitSourceDAO;
import by.tc.like_it.dao.pool.ConnectionPool;
import by.tc.like_it.dao.pool.exception.ConnectionPoolException;
import by.tc.like_it.dao.exception.DAOException;

public class InitSourceDAOImpl implements InitSourceDAO {
    @Override
    public void initSource() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void destroySource() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.destroy();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }
}
