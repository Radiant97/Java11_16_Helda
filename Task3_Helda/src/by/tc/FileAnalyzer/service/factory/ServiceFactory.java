package by.tc.FileAnalyzer.service.factory;

import by.tc.FileAnalyzer.service.FileService;
import by.tc.FileAnalyzer.service.impl.FileServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final FileService fileService = new FileServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() { return instance; }

    public FileService getFileService() { return fileService; }
}
