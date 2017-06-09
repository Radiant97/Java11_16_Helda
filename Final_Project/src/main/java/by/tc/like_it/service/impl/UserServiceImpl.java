package by.tc.like_it.service.impl;

import by.tc.like_it.bean.User;
import by.tc.like_it.dao.UserDAO;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.dao.factory.DAOFactory;
import by.tc.like_it.service.UserService;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;
import by.tc.like_it.service.util.DataValidator;
import by.tc.like_it.service.util.ServiceUtil;

import java.util.Date;

public class UserServiceImpl implements UserService{

    @Override
    public User getUserById(int id) throws ServiceException, ServiceWrongDataException {
        DataValidator.validateId(id);

        User user;
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            user = userDAO.getUserById(id);
            if (user == null) {
                throw new ServiceWrongDataException("User with " + id + " id doesn't exist.");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User signUp(String email, String nickname, String password, String confirmPassword)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateEmail(email);
        DataValidator.validateNickname(nickname);
        DataValidator.validatePassword(password, confirmPassword);

        String encryptedPassword = ServiceUtil.EncryptPassword(password);

        User user;
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            user = userDAO.addUser(email, nickname, encryptedPassword, new Date());
        } catch (DAOException e) {
            throw new ServiceException("Error during sign up process.", e);
        }
        return user;
    }

    @Override
    public User signIn(String email, String password) throws ServiceException, ServiceWrongDataException {
        DataValidator.validateEmail(email);
        DataValidator.validatePassword(password);

        User user;
        String encryptedPassword = ServiceUtil.EncryptPassword(password);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            user = userDAO.getUserByEmail(email);

            if (user == null) {
                throw new ServiceWrongDataException("User with such email does not exist.");
            }
            if (!user.getPassword().equals(encryptedPassword)) {
                throw new ServiceWrongDataException("Wrong password.");
            }
        } catch (DAOException e ) {
            throw new ServiceException("Error during sign in process.", e);
        }
        return user;
    }

    @Override
    public User editUser(User user, String confirmPassword)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateId(user.getId());
        DataValidator.validateEmail(user.getEmail());
        DataValidator.validatePassword(user.getPassword(), confirmPassword);
        DataValidator.validateNickname(user.getNickname());
        DataValidator.validateRealName(user.getRealName());
        DataValidator.validateLocation(user.getLocation());

        user.setPassword(ServiceUtil.EncryptPassword(user.getPassword()));

        User newUser;
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            newUser = userDAO.editUser(user);
        } catch (DAOException e){
            throw new ServiceException("Error during editing user.", e);
        }
        if (newUser == null) {
            throw new ServiceWrongDataException("Can not edit user.");
        }
        return newUser;
    }

    @Override
    public void bavUser(int id) throws ServiceException, ServiceWrongDataException {
        DataValidator.validateId(id);

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.banUser(id);
        }  catch (DAOException e){
            throw new ServiceException("Error during ban user.", e);
        }
    }
}
