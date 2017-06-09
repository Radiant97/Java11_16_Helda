package by.tc.like_it.dao.impl;

import by.tc.like_it.bean.Tag;
import by.tc.like_it.dao.TagDAO;
import by.tc.like_it.dao.config.SQLQuery;
import by.tc.like_it.dao.pool.ConnectionPool;
import by.tc.like_it.dao.pool.exception.ConnectionPoolException;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.util.BeanCreator;

import java.sql.*;
import java.util.List;
import java.util.Locale;

public class TagDAOImpl implements TagDAO {

    @Override
    public List<Tag> getAllTags() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Tag> tagList = null;
        try {
            connection = connectionPool.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQuery.GET_ALL_TAGS_SQL);
            tagList = BeanCreator.createTagList(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
        return tagList;
    }

    @Override
    public List<Tag> getTags(int startFrom, int count, Locale locale)
            throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String sqlQuery = locale.getLanguage().equals(Locale.ENGLISH.getLanguage()) ?
                SQLQuery.GET_TAGS_FROM_COUNT_EN
                : SQLQuery.GET_TAGS_FROM_COUNT;

        List<Tag> tagList = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(sqlQuery);
            ps.setInt(1, startFrom);
            ps.setInt(2, count);
            resultSet = ps.executeQuery();

            tagList = BeanCreator.createTagList(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.close(connection, ps, resultSet);
        }
        return tagList;
    }

    @Override
    public Tag addTag(String name, Locale locale) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlQuery = locale.getLanguage().equals(Locale.ENGLISH.getLanguage()) ?
                SQLQuery.ADD_TAG_SQL_EN
                : SQLQuery.ADD_TAG_SQL;

        int tagId = 0;
        Tag tag = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            if (ps.executeUpdate() > 0) {
                rs  = ps.getGeneratedKeys();
                if (rs.next()) {
                    tagId =  rs.getInt(1);
                }
                tag = getTagById(tagId);
            }
        }catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException("Error during addition tag process.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return tag;
    }

    @Override
    public boolean deleteTag(int tagId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        boolean isDeleted = false;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.DELETE_TAG_SQL);
            ps.setInt(1, tagId);
            if (ps.executeUpdate() != 0) {
                isDeleted = true;
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException("Error during deleting tag process.", e);
        } finally {
            connectionPool.close(connection, ps);
        }
        return isDeleted;
    }

    @Override
    public Tag getTagById(int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Tag tag = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.GET_TAG_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()){
                throw new DAOException("Tag with such id does not exist.");
            }
            tag = BeanCreator.createTag(rs);
        }
        catch (SQLException e) {
            throw new DAOException("Error during getting tag by id process.", e);
        } catch (ConnectionPoolException e){
            throw new DAOException("Can not get a connection.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return tag;
    }

    @Override
    public Tag getTagByName(String name) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Tag tag = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.GET_TAG_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()){
                tag = BeanCreator.createTag(rs);
            }
        }
        catch (SQLException e) {
            throw new DAOException("Error during getting tag by name process.", e);
        } catch (ConnectionPoolException e){
            throw new DAOException("Can not get a connection.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return tag;
    }
}
