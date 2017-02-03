package by.tc.FileAnalyzer.service.factory;

import by.tc.FileAnalyzer.service.Analyzer;
import by.tc.FileAnalyzer.service.impl.AnalyzerImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final Analyzer analyzer = new AnalyzerImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() { return instance; }

    public Analyzer getAnalyzer() { return analyzer; }
}
