package by.tc.like_it.service;

import by.tc.like_it.bean.User;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;

public interface UserService {
    User signIn(String email, String password)
            throws ServiceException, ServiceWrongDataException;
    User signUp(String email, String nickname, String password, String confirmPassword)
            throws ServiceException, ServiceWrongDataException;
    User getUserById(int id) throws ServiceException, ServiceWrongDataException;
    User editUser(User user, String confirmPassword)
            throws ServiceException, ServiceWrongDataException;
    void bavUser(int id) throws ServiceException, ServiceWrongDataException;
}
