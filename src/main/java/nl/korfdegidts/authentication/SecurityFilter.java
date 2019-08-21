/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 11/1/18 2:28 PM
 */

package nl.korfdegidts.authentication;

import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.MethodDoesNotExist;
import nl.korfdegidts.exception.UserNotFoundException;
import nl.korfdegidts.service.ILoginService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTH_HEADER = "token";
    private SecureAnnotationChecker secureAnnotationChecker = new SecureAnnotationChecker();

    private ILoginService loginService;
    @Context
    private ResourceInfo resourceInfo;

    @Inject
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        AuthenticatedUser.setAuthenticatedUser(null);

        Class targetClass = resourceInfo.getResourceClass();

        Method targetMethod = resourceInfo.getResourceMethod();

        try {
            if (secureAnnotationChecker.methodIsSecured(targetClass, targetMethod)) {
                User user = getUserFromRequest(containerRequestContext);

                if (userHasCorrectRole(user, targetClass, targetMethod)) {
                    AuthenticatedUser.setAuthenticatedUser(user);
                } else {
                    throw new AuthenticationException();
                }
            }
        } catch (MethodDoesNotExist e) {
            containerRequestContext.abortWith(Response.status(Response.Status.NOT_FOUND).build());
        } catch (AuthenticationException a) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }

    private String getTokenFromQueryParam(ContainerRequestContext containerRequestContext) throws AuthenticationException {
        List<String> authHeaders =
                containerRequestContext.getUriInfo().
                        getQueryParameters().get(AUTH_HEADER);

        if (authHeaders == null || authHeaders.isEmpty()) {
            throw new AuthenticationException();
        }
        return authHeaders.get(0);
    }

    private User getUserFromRequest(ContainerRequestContext containerRequestContext) throws AuthenticationException {
        String token = getTokenFromQueryParam(containerRequestContext);

        if (token == null) {
            throw new AuthenticationException();
        }

        User user = null;
        try {
            user = loginService.getUserFromToken(token);
        } catch (UserNotFoundException e) {
            throw new AuthenticationException();
        }

        return user;
    }

    private boolean userHasCorrectRole(User user, Class targetClass, Method targetMethod) throws MethodDoesNotExist {

        return secureAnnotationChecker.userHasCorrectRoleForMethod(targetClass, targetMethod, user.getRole());
    }
}
