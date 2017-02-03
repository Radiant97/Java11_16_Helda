package by.tc.FileAnalyzer.view;


import by.tc.FileAnalyzer.bean.NodeInfo;
import by.tc.FileAnalyzer.service.Analyzer;
import by.tc.FileAnalyzer.service.exception.ServiceException;
import by.tc.FileAnalyzer.service.factory.ServiceFactory;

public class Runner {
    private final static String FILE_PATH = "src\\by\\tc\\FileAnalyzer\\source\\notes.xml";

    public static void main(String[] args){
        ServiceFactory sf = ServiceFactory.getInstance();
        Analyzer a = sf.getAnalyzer();
        try {
            a.setFile(FILE_PATH);
            NodeInfo node;
            node = a.next();
            while (node != null){
                System.out.println(node.getContent() + ": " + node.getType());
                node = a.next();
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
