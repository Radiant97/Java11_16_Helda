import by.tc.like_it.bean.User;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.impl.user.BanUserCommand;
import by.tc.like_it.dao.UserDAO;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.dao.factory.DAOFactory;
import by.tc.like_it.dao.pool.ConnectionPool;
import by.tc.like_it.dao.pool.exception.ConnectionPoolException;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.assertEquals;

/**
 * Created by Ananas on 09.06.2017.
 */
public class BanUserTest {

    @Test
    public void test () throws ConnectionPoolException {
        User user = new User();
        user.setNickname("testBan");
        user.setPassword("qweqwe");
        user.setRegistrationDate(new java.sql.Date(new Date().getTime()));
        user.setEmail("qweqwe");

        User newUser = null;

        ConnectionPool.getInstance().init();
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.banUser(1);
            newUser = userDAO.getUserById(1);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        assertEquals(true, newUser.isBaned());
    }
}
