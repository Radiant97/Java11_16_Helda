package by.tc.FileAnalyzer.service;

import by.tc.FileAnalyzer.bean.NodeInfo;
import by.tc.FileAnalyzer.service.exception.ServiceException;

public interface FileService {
    NodeInfo next() throws ServiceException;
    void openFile(String filePath) throws ServiceException;
}
