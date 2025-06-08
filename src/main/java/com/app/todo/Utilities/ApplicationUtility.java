package com.app.todo.Utilities;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class ApplicationUtility {

    public static final String TITLE_NOT_FOUND = "Title not found";
    public static final String SUB_USER = "sub";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String NOTE = "Note ";
    public static final String SUCCESSFUL_DELETION = "has deleted successfully";
    public static final String NOT_PERMITTED_MESSAGE = "You are not permitted to edit this note";
    public static final String NOT_VIEW_PERMITTED_MESSAGE = "You are not permitted to view this note";

    public static String userAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = (String) jwt.getClaims().get(SUB_USER);
        return userId;
    }

}
