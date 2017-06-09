package by.tc.like_it.dao.impl;

import by.tc.like_it.bean.User;
import by.tc.like_it.dao.config.SQLQuery;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.dao.UserDAO;
import by.tc.like_it.dao.pool.ConnectionPool;
import by.tc.like_it.dao.pool.exception.ConnectionPoolException;
import by.tc.like_it.util.BeanCreator;

import java.sql.*;

public class UserDAOImpl implements UserDAO{

    @Override
    public User getUserById(int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.GET_USER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()){
                throw new DAOException("User with such id does not exist.");
            }
            user = BeanCreator.createUser(rs);
        }
        catch (SQLException e) {
            throw new DAOException("Error during getting user by id process.", e);
        } catch (ConnectionPoolException e){
            throw new DAOException("Can not get a connection.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return user;
    }

    @Override
    public User addUser(String email, String nickname, String password, java.util.Date registration_date) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.ADD_USER_SQL);
            ps.setString(1, email);
            ps.setString(2, nickname);
            ps.setString(3, password);
            ps.setDate(4, new java.sql.Date(registration_date.getTime()));
            if (ps.executeUpdate() > 0) {
                user = getUserByEmail(email);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e){
            throw new DAOException("Can not get a connection.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.GET_USER_BY_EMAIL_SQL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (!rs.next()){
                throw new DAOException("User with such email does not exist.");
            }
            user = BeanCreator.createUser(rs);
        }
        catch (SQLException e) {
            throw new DAOException("Error during getting user by email process.", e);
        } catch (ConnectionPoolException e){
            throw new DAOException("Can not get a connection.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return user;
    }

    @Override
    public User editUser(User user) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int newUserId = 0;
        User newUser = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.UPDATE_USER_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            if (user.getRealName() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, user.getRealName());
            }
            ps.setString(4, user.getNickname());
            if (user.getRealName() == null) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, user.getLocation());
            }
            ps.setInt(6, user.getId());

            if (ps.executeUpdate() > 0) {
                rs  = ps.getGeneratedKeys();
                if (rs.next()) {
                    newUserId =  rs.getInt(1);
                }
                newUser = getUserById(newUserId);
            }
        }catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return newUser;
    }

    @Override
    public void banUser(int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.BANN_USER);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e){
            throw new DAOException("Can not get a connection.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
    }
}
