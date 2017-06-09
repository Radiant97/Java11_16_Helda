package by.tc.like_it.dao;

import by.tc.like_it.bean.User;
import by.tc.like_it.dao.exception.DAOException;

import java.util.Date;

public interface UserDAO {
    User getUserByEmail(String email)throws DAOException;
    User addUser(String email, String nickname, String password, Date registration_date) throws DAOException;
    User getUserById(int id) throws DAOException;
    User editUser(User user) throws DAOException;
    void banUser(int id) throws DAOException;
}
