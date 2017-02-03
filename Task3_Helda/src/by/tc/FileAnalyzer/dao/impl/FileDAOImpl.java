package by.tc.FileAnalyzer.dao.impl;

import by.tc.FileAnalyzer.dao.FileDAO;
import by.tc.FileAnalyzer.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileDAOImpl implements FileDAO{
    @Override
    public BufferedReader openFile(String filePath) throws DAOException {
        if (filePath == null || filePath.isEmpty()){
            throw new DAOException("Incorrect file path.");
        }
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found.");
        }
        return fileReader;
    }
}
