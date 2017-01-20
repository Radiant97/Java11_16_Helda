package by.tc.FileAnalyzer.service.impl;

import by.tc.FileAnalyzer.bean.NodeInfo;
import by.tc.FileAnalyzer.bean.NodeType;
import by.tc.FileAnalyzer.dao.FileDAO;
import by.tc.FileAnalyzer.dao.exception.DAOException;
import by.tc.FileAnalyzer.dao.factory.DAOFactory;
import by.tc.FileAnalyzer.service.FileService;
import by.tc.FileAnalyzer.service.exception.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileServiceImpl implements FileService{
    private StringBuilder buffer;

    private Pattern tagPattern = Pattern.compile("<(([^>]|\n)+)>|o");
    private Pattern contentPattern = Pattern.compile(">[^<\n]+<");
    private Matcher tagMatcher;
    private Matcher contentMatcher;

    @Override
    public void openFile(String filePath) throws ServiceException {
        if (filePath == null || filePath.isEmpty()){
            throw new ServiceException("Incorrect file path.");
        }
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            FileDAO fileDAO = daoFactory.getFileDAO();

            fileDAO.openFile(filePath);
            
            fileDAO.read();
            buffer = new StringBuilder(fileDAO.read());
            tagMatcher = tagPattern.matcher(buffer);
            contentMatcher = contentPattern.matcher(buffer);
        } catch (DAOException e) {
            throw new ServiceException("Error during opening file process.");
        }
    }

    @Override
    public NodeInfo next() throws ServiceException {
        while (!tagMatcher.find()){
            DAOFactory daoFactory = DAOFactory.getInstance();
            FileDAO fileDAO = daoFactory.getFileDAO();
            try {
                String line = fileDAO.read();
                if (line != null) {
                    buffer.append(line);
                } else {
                    return null;
                }
                tagMatcher.reset();
            } catch (DAOException e) {
                throw  new ServiceException("Error during getting next tag.");
            }
        }
        return nextProcess();
    }

    private NodeInfo nextProcess(){
        int tagMatchStartPosition;
        int contentMatchStartPosition = buffer.length();
        int tagMatchEndPosition;
        int contentMatchEndPosition = buffer.length();

        tagMatchStartPosition = tagMatcher.start();
        tagMatchEndPosition = tagMatcher.end();
        if (contentMatcher.find()) {
            contentMatchStartPosition = contentMatcher.start();
            contentMatchEndPosition = contentMatcher.end();
        }
        if (tagMatchStartPosition < contentMatchStartPosition) {
            String tag = buffer.substring(tagMatchStartPosition, tagMatchEndPosition);
            buffer.delete(0, tagMatchEndPosition - 1);
            contentMatcher.reset();
            tagMatcher.reset();

            NodeInfo node = defineNode(tag);
            return node;

        } else {
            String content = buffer.substring(contentMatchStartPosition + 1, contentMatchEndPosition - 1);
            buffer.delete(0, contentMatchEndPosition - 1);
            tagMatcher.reset();
            contentMatcher.reset();

            NodeInfo node = defineNode(content);
            return node;
        }
    }

    private NodeInfo defineNode(String tag){
        String tagContent = tag;
        NodeInfo node = new NodeInfo();

        if (tag.indexOf("/>") != -1){
            node.setType(NodeType.NO_BODY_TAG);
            tagContent = tag.substring(1, tag.length() - 2);
            node.setContent(tagContent);

        } else if (tag.indexOf("</") != -1){
            node.setType(NodeType.CLOSING_TAG);
            tagContent = tag.substring(2, tag.length() -1);
            node.setContent(tagContent);

        } else if (tag.indexOf("<") != -1){
            node.setType(NodeType.OPENING_TAG);
            tagContent = tag.substring(1, tag.length() -1);
            node.setContent(tagContent);

        } else {
            node.setType(NodeType.TAG_CONTENTS);
            node.setContent(tag);
        }
        return node;
    }
}
