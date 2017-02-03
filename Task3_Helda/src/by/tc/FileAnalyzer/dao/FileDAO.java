package by.tc.FileAnalyzer.dao;

import by.tc.FileAnalyzer.dao.exception.DAOException;

import java.io.BufferedReader;

public interface FileDAO {
    BufferedReader openFile(String filePath) throws DAOException;
}
