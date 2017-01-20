package by.tc.FileAnalyzer.service;

import by.tc.FileAnalyzer.service.exception.ServiceException;

public interface FileService {
    String next() throws ServiceException;
    void openFile(String filePath) throws ServiceException;
}
