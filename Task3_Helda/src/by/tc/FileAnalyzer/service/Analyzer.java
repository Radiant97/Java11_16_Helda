package by.tc.FileAnalyzer.service;

import by.tc.FileAnalyzer.bean.NodeInfo;
import by.tc.FileAnalyzer.service.exception.ServiceException;

public interface Analyzer {
    NodeInfo next() throws ServiceException;
    void setFile(String filePath) throws ServiceException;
}
