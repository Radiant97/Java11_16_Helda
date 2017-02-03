package by.tc.FileAnalyzer.dao;

import by.tc.FileAnalyzer.dao.exception.DAOException;

import java.io.IOException;

public interface FileDAO{
    void openFile(String filePath) throws DAOException;
    String read() throws DAOException;
    void close() throws IOException;
}
