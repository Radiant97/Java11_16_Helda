package by.tc.like_it.controller.listener;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.CommandProvider;
import by.tc.like_it.controller.command.config.CommandName;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import java.io.IOException;

public class InitSourceListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(InitSourceListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Command command = CommandProvider.getInstance().getCommand(CommandName.INIT_SOURCE.name());
        try {
            command.execute(null, null);
        } catch (ServletException | IOException e) {
            LOGGER.error("Can't initialize connection pool.", e);
            throw new RuntimeException("Can not initialize connection pool.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Command command = CommandProvider.getInstance().getCommand(CommandName.DESTROY_SOURCE.name());
        try {
            command.execute(null, null);
        } catch (ServletException | IOException e) {
            LOGGER.error("Can not destroy connection pool.", e);
            throw new RuntimeException("Can not destroy connection pool.", e);
        }
    }
}
