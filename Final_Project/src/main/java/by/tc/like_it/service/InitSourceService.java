package by.tc.like_it.service;

import by.tc.like_it.service.exception.ServiceException;

public interface InitSourceService {
    void initSource() throws ServiceException;
    void destroySource() throws ServiceException;
}
