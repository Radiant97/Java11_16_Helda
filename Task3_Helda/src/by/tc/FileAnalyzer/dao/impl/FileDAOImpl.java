package by.tc.FileAnalyzer.dao.impl;

import by.tc.FileAnalyzer.dao.FileDAO;
import by.tc.FileAnalyzer.dao.exception.DAOException;

import java.io.*;

public class FileDAOImpl implements FileDAO, Closeable{
    private BufferedReader xmlFile;

    @Override
    public void openFile(String filePath) throws DAOException {
        if (filePath == null || filePath.isEmpty()){
            throw new DAOException("Incorrect file path.");
        }
        try {
            xmlFile = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found.");
        }
    }

    @Override
    public String read() throws DAOException {
        String line;
        try {
            line = xmlFile.readLine();
        } catch (IOException e) {
            throw new DAOException("Error during reading file process.");
        }
        return line;
    }

    @Override
    public void close() throws IOException {
        xmlFile.close();
    }
}
