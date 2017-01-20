package by.tc.FileAnalyzer.service.impl;

import by.tc.FileAnalyzer.dao.FileDAO;
import by.tc.FileAnalyzer.dao.exception.DAOException;
import by.tc.FileAnalyzer.dao.factory.DAOFactory;
import by.tc.FileAnalyzer.service.FileService;
import by.tc.FileAnalyzer.service.exception.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileServiceImpl implements FileService{
    private StringBuilder buffer;

    private Pattern p = Pattern.compile("<(([^>]|\n)+)>|o");
    private Pattern p1 = Pattern.compile(">[^<\n]+<");
    private Matcher m;
    private Matcher m1;

    @Override
    public void openFile(String filePath) throws ServiceException {
        if (filePath == null || filePath.isEmpty()){
            throw new ServiceException("Incorrect file path.");
        }
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            FileDAO fileDAO = daoFactory.getFileDAO();

            fileDAO.openFile(filePath);
            buffer = new StringBuilder(fileDAO.read());
            m = p.matcher(buffer);
            m1 = p1.matcher(buffer);
        } catch (DAOException e) {
            throw new ServiceException("Error during opening file process.");
        }
    }

    @Override
    public String next() throws ServiceException {
        while (!m.find()){
            DAOFactory daoFactory = DAOFactory.getInstance();
            FileDAO fileDAO = daoFactory.getFileDAO();
            try {
                String line = fileDAO.read();
                if (line != null) {
                    buffer.append(line);
                } else {
                    return null;
                }
                m.reset();
            } catch (DAOException e) {
                throw  new ServiceException("Error during getting next tag.");
            }
        }
        return nextProcess();
    }

    private String nextProcess(){
        int mStart;
        int m1Start = buffer.length();
        int mEnd;
        int m1End = buffer.length();

        mStart = m.start();
        mEnd = m.end();
        if (m1.find()) {
            m1Start = m1.start();
            m1End = m1.end();
        }
        if (mStart < m1Start) {
            String name = buffer.substring(mStart + 1, mEnd - 1);
            buffer.delete(0, mEnd - 1);
            m1.reset();
            m.reset();

            return name;

        } else {
            String content = buffer.substring(m1Start + 1, m1End - 1);
            buffer.delete(0, m1End - 1);
            m.reset();
            m1.reset();

            return content;
        }
    }
}
