package by.tc.like_it.service.impl;

import by.tc.like_it.dao.InitSourceDAO;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.dao.factory.DAOFactory;
import by.tc.like_it.service.InitSourceService;
import by.tc.like_it.service.exception.ServiceException;

public class InitSourceServiceImpl implements InitSourceService{
    @Override
    public void initSource() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        InitSourceDAO initializationDAO = daoFactory.getInitSourceDAO();
        try {
            initializationDAO.initSource();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void destroySource() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        InitSourceDAO initializationDAO = daoFactory.getInitSourceDAO();
        try {
            initializationDAO.destroySource();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
