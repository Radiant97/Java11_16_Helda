package by.tc.FileAnalyzer.controller;

import by.tc.FileAnalyzer.bean.NodeInfo;
import by.tc.FileAnalyzer.service.FileService;
import by.tc.FileAnalyzer.service.exception.ServiceException;
import by.tc.FileAnalyzer.service.factory.ServiceFactory;

import java.util.logging.Logger;

public final class Analizator{
    private static final Logger log = Logger.getLogger(Analizator.class.getName());

    public void setFile(String filePath){
        try{
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FileService fileService = serviceFactory.getFileService();

            fileService.openFile(filePath);
        } catch (ServiceException e){
            log.info(e.getMessage());
        }
    }

    public NodeInfo next(){
        NodeInfo node;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FileService fileService = serviceFactory.getFileService();
        try{
            node = fileService.next();
            return node;
        } catch (ServiceException e){
            log.info(e.getMessage());
            return null;
        }
    }
}
