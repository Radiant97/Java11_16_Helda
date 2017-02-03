package by.tc.FileAnalyzer.service.impl;

import by.tc.FileAnalyzer.bean.NodeInfo;
import by.tc.FileAnalyzer.bean.NodeType;
import by.tc.FileAnalyzer.dao.FileDAO;
import by.tc.FileAnalyzer.dao.exception.DAOException;
import by.tc.FileAnalyzer.dao.factory.DAOFactory;
import by.tc.FileAnalyzer.service.Analyzer;
import by.tc.FileAnalyzer.service.exception.ServiceException;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzerImpl implements Analyzer, Closeable{
    private StringBuilder tagBuffer;
    private final static String BUFF_INITIALIZER = "";

    private final static String TAG_REG_EX = "<([^>]|\n)+>";
    private final static String CONTENT_REG_EX = ">[^<\n]+<";
    private final static String XML_DECLARATION_REG_EX= "<?([^>])+?>";

    private final static String NO_BODY_TAG_SIGN = "/>";
    private final static String CLOSING_TAG_SIGN = "</";
    private final static String OPENING_TAG_SIGN = "<";

    private Pattern tagPattern ;
    private Pattern contentPattern;
    private Pattern xmlDeclarationPattern ;
    private Matcher tagMatcher;
    private Matcher contentMatcher;
    private Matcher xmlDeclarationMatcher;

    private BufferedReader xmlFile;

    public AnalyzerImpl(){
        tagBuffer = new StringBuilder(BUFF_INITIALIZER);

        tagPattern = Pattern.compile(TAG_REG_EX);
        contentPattern = Pattern.compile(CONTENT_REG_EX);
        xmlDeclarationPattern = Pattern.compile(XML_DECLARATION_REG_EX);
        tagMatcher = tagPattern.matcher(tagBuffer);
        contentMatcher = contentPattern.matcher(tagBuffer);
        xmlDeclarationMatcher = xmlDeclarationPattern.matcher(tagBuffer);
    }

    @Override
    public void setFile(String filePath) throws ServiceException {
        if (filePath == null || filePath.isEmpty()){
            throw new ServiceException("Incorrect file path.");
        }

        //чистка буфера
        tagBuffer.setLength(0);

        DAOFactory daoFactory = DAOFactory.getInstance();
        FileDAO fileDAO = daoFactory.getFileDAO();
        try {
            xmlFile = fileDAO.openFile(filePath);
            skipXMLDeclaration();
        } catch (DAOException e) {
            throw new ServiceException("Error during opening file process.");
        }
    }

    @Override
    public NodeInfo next() throws ServiceException {
        if (!readXMLFileWhileNotMatch(tagMatcher)) { return null; }
        NodeInfo node = nextProcess();
        return node;
    }


    @Override
    public void close() throws IOException {
        xmlFile.close();
    }

    private void skipXMLDeclaration() throws ServiceException{
        readXMLFileWhileNotMatch(xmlDeclarationMatcher);
        tagBuffer.delete(0, xmlDeclarationMatcher.end() - 1);
    }

    private boolean readXMLFileWhileNotMatch(Matcher matcher) throws ServiceException{
        while (!matcher.find()){
            if (!readXMLFile()) { return false; }
            matcher.reset();
        }
        return true;
    }

    private boolean readXMLFile() throws ServiceException{
        boolean result;
        try {
            String line = xmlFile.readLine();
            if (line != null) {
                tagBuffer.append(line);
                result = true;
            } else {
                result = false;
            }
        } catch (IOException e) {
            throw  new ServiceException("Error during reading xml file.");
        }
        return result;
    }

    private NodeInfo nextProcess(){
        int tagStartPosition;
        int contentStartPosition = tagBuffer.length();
        int tagEndPosition;
        int contentEndPosition = tagBuffer.length();

        tagStartPosition = tagMatcher.start();
        tagEndPosition = tagMatcher.end();
        if (contentMatcher.find()) {
            contentStartPosition = contentMatcher.start();
            contentEndPosition = contentMatcher.end();
        }
        if (tagStartPosition < contentStartPosition) {
            String tag = tagBuffer.substring(tagStartPosition, tagEndPosition);
            tagBuffer.delete(0, tagEndPosition - 1);
            contentMatcher.reset();
            tagMatcher.reset();

            NodeInfo node = defineNode(tag);
            return node;

        } else {
            String content = tagBuffer.substring(contentStartPosition + 1, contentEndPosition - 1);
            tagBuffer.delete(0, contentEndPosition - 1);
            tagMatcher.reset();
            contentMatcher.reset();

            NodeInfo node = defineNode(content);
            return node;
        }
    }

    private NodeInfo defineNode(String tag){
        String tagContent = tag;
        NodeInfo node = new NodeInfo();

        if (tag.indexOf(NO_BODY_TAG_SIGN) != -1){
            node.setType(NodeType.NO_BODY_TAG);
            tagContent = tag.substring(1, tag.length() - 2);
            node.setContent(tagContent);

        } else if (tag.indexOf(CLOSING_TAG_SIGN) != -1){
            node.setType(NodeType.CLOSING_TAG);
            tagContent = tag.substring(2, tag.length() -1);
            node.setContent(tagContent);

        } else if (tag.indexOf(OPENING_TAG_SIGN) != -1){
            node.setType(NodeType.OPENING_TAG);
            tagContent = tag.substring(1, tag.length() -1);
            node.setContent(tagContent);

        } else {
            node.setType(NodeType.TAG_CONTENTS);
            node.setContent(tagContent);
        }
        return node;
    }

}
