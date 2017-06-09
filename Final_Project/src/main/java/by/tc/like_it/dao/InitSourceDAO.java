package by.tc.like_it.dao;

import by.tc.like_it.dao.exception.DAOException;

public interface InitSourceDAO {
    void initSource() throws DAOException;
    void destroySource() throws DAOException;
}
