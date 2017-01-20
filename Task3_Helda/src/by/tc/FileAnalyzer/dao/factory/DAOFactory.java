package by.tc.FileAnalyzer.dao.factory;

import by.tc.FileAnalyzer.dao.FileDAO;
import by.tc.FileAnalyzer.dao.impl.FileDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final FileDAO fileDAO = new FileDAOImpl();

    private DAOFactory(){}

    public static DAOFactory getInstance() { return  instance; }

    public FileDAO getFileDAO() { return fileDAO; }
}
