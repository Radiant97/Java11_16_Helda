package by.tc.like_it.dao;

import by.tc.like_it.bean.Tag;
import by.tc.like_it.dao.exception.DAOException;

import java.util.List;
import java.util.Locale;

public interface TagDAO {
    List<Tag> getAllTags() throws DAOException;
    List<Tag> getTags(int startFrom, int count, Locale locale) throws DAOException;
    Tag addTag(String name, Locale locale) throws DAOException;
    boolean deleteTag(int tagId) throws DAOException;
    Tag getTagById(int id) throws DAOException;
    Tag getTagByName(String name) throws DAOException;
}
