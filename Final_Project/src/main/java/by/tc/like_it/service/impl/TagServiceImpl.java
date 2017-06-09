package by.tc.like_it.service.impl;

import by.tc.like_it.bean.Tag;
import by.tc.like_it.dao.TagDAO;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.dao.factory.DAOFactory;
import by.tc.like_it.service.TagService;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;
import by.tc.like_it.service.util.DataValidator;

import java.util.List;
import java.util.Locale;

public class TagServiceImpl implements TagService {
    @Override
    public List<Tag> getAllTags() throws ServiceException {
        List<Tag> tagList;

        DAOFactory daoFactory = DAOFactory.getInstance();
        TagDAO themeDAO = daoFactory.getTagDAO();
        try {
            tagList = themeDAO.getAllTags();
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return tagList;
    }

    @Override
    public List<Tag> getTags(int startFrom, int count, Locale locale)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateStartFromElement(startFrom);
        DataValidator.validateElementCount(count);

        List<Tag> tagList;

        DAOFactory daoFactory = DAOFactory.getInstance();
        TagDAO themeDAO = daoFactory.getTagDAO();
        try {
            tagList = themeDAO.getTags(startFrom, count, locale);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return tagList;
    }

    @Override
    public Tag addTag(String name, Locale locale) throws ServiceException, ServiceWrongDataException {
        DataValidator.validateTagName(name);

        Tag tag;
        DAOFactory daoFactory = DAOFactory.getInstance();
        TagDAO tagDAO = daoFactory.getTagDAO();
        try {
            tag = tagDAO.addTag(name, locale);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tag;
    }

    @Override
    public boolean deleteTag(int tagId) throws ServiceException, ServiceWrongDataException {
        DataValidator.validateId(tagId);

        boolean isDeleted;
        DAOFactory daoFactory = DAOFactory.getInstance();
        TagDAO tagDAO = daoFactory.getTagDAO();
        try {
            isDeleted = tagDAO.deleteTag(tagId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }
}
