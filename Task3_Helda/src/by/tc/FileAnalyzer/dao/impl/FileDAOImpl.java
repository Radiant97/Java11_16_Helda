package by.tc.FileAnalyzer.dao.impl;

import by.tc.FileAnalyzer.dao.FileDAO;
import by.tc.FileAnalyzer.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileDAOImpl implements FileDAO{
    private BufferedReader fileReader;
    private int offset;
    private final int length = 200;

    @Override
    public String read() throws DAOException {
        //char[] buff = null;
        try {
            String s = fileReader.readLine();
            return s;
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
            offset = 0;
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found.");
        }
    }
}
