package by.tc.like_it.service;

import by.tc.like_it.bean.Tag;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;

import java.util.List;
import java.util.Locale;

public interface TagService {
    List<Tag> getAllTags() throws ServiceException;
    List<Tag> getTags(int startFrom, int count, Locale locale)
            throws ServiceException, ServiceWrongDataException;
    Tag addTag(String name, Locale locale) throws ServiceException, ServiceWrongDataException;
    boolean deleteTag(int themeId) throws ServiceException, ServiceWrongDataException;
}
