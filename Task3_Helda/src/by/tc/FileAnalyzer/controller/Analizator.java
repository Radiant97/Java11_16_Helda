package by.tc.FileAnalyzer.controller;

import by.tc.FileAnalyzer.bean.NodeInfo;
import by.tc.FileAnalyzer.service.FileService;
import by.tc.FileAnalyzer.service.exception.ServiceException;
import by.tc.FileAnalyzer.service.factory.ServiceFactory;

import java.io.Closeable;
import java.io.IOException;

public final class Analizator implements Closeable{
    public void setFile(String filePath){
        try{
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FileService fileService = serviceFactory.getFileService();

            fileService.openFile(filePath);
        } catch (Exception e){
            //logger
            System.out.println(e.getMessage());
        }
    }

    public String next(){
        //NodeInfo nodeInfo = null;
        String node;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FileService fileService = serviceFactory.getFileService();
        try{
            node = fileService.next();
            return node;
        } catch (ServiceException e){
            //logger
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        //service.close?
    }
}
