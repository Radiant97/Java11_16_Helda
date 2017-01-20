package by.tc.FileAnalyzer.dao.impl;

import by.tc.FileAnalyzer.dao.FileDAO;
import by.tc.FileAnalyzer.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileDAOImpl implements FileDAO, AutoCloseable{
    private BufferedReader fileReader;

    @Override
    public String read() throws DAOException {
        try {
            String line = fileReader.readLine();
            return line;
        } catch (IOException e) {
            throw new DAOException("Error during reading file process.");
        }
    }

    @Override
    public void openFile(String filePath) throws DAOException {
        if (filePath == null || filePath.isEmpty()){
            throw new DAOException("Incorrect file path.");
        }
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found.");
        }
    }

    @Override
    public void close() throws Exception {
        fileReader.close();
    }
}
