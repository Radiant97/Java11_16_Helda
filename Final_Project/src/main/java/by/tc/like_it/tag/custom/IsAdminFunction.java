package by.tc.like_it.tag.custom;

import by.tc.like_it.controller.command.config.AttributeName;

public class IsAdminFunction {
    public static boolean isAdmin(Object obj) {
        if (obj != null && obj.toString().equals(AttributeName.ADMIN)) {
            return true;
        }
        return false;
    }
}
