package by.tc.like_it.controller.command.util;

import by.tc.like_it.controller.command.config.AttributeName;
import by.tc.like_it.controller.command.config.JSPPageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class RequestHandler {
    private final static String REFERRER = "referer";

    private RequestHandler(){}

    public static String getPreviousPage(HttpServletRequest request){
        String previousPage = request.getHeader(REFERRER);
        return previousPage != null ? previousPage : JSPPageName.MAIN_PAGE;
    }

    public static Locale getLocale(HttpServletRequest request){
        HttpSession session = request.getSession();
        return session.getAttribute(AttributeName.LANGUAGE) != null
                ? new Locale(((String)session.getAttribute(AttributeName.LANGUAGE)).split("_")[0])
                : request.getLocale();
    }
}
