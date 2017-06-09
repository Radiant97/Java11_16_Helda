package by.tc.like_it.controller.command.impl.initialization;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.service.InitSourceService;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DestroySourceCommand implements Command{
    private static final Logger LOGGER = Logger.getLogger(DestroySourceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InitSourceService initSourceService = serviceFactory.getInitSourceService();
        try {
            initSourceService.destroySource();
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
    }
}
