/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 11/1/18 2:23 PM
 */

package nl.korfdegidts.authentication;

import nl.korfdegidts.exception.AnnotationNotFoundException;
import nl.korfdegidts.exception.MethodDoesNotExist;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class SecureAnnotationChecker {

    public boolean methodIsSecured(Class targetClass, Method targetMethod) throws MethodDoesNotExist {

        methodAndClassAreSet(targetClass, targetMethod);

        try {
            Secure secureAnnotation = getAnnotationFromMethod(targetClass, targetMethod);

        } catch (NoSuchMethodException e) {
            throw new MethodDoesNotExist();
        } catch (AnnotationNotFoundException a) {
            return false;
        }
        return true;
    }

    public boolean userHasCorrectRoleForMethod(Class targetClass, Method targetMethod, Role role) throws MethodDoesNotExist {
        methodAndClassAreSet(targetClass, targetMethod);

        try {
            Secure sec = getAnnotationFromMethod(targetClass, targetMethod);
            for (Role annoRole : sec.allowedRoles()) {
                if (annoRole == role) {
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            return false;
        }

        return false;
    }

    private void methodAndClassAreSet(Class targetClass, Method targetMethod) throws MethodDoesNotExist {
        if (targetClass == null || targetMethod == null) {
            throw new MethodDoesNotExist();
        }
    }

    private Secure getAnnotationFromMethod(Class targetClass, Method targetMethod) throws NoSuchMethodException, AnnotationNotFoundException {

        Method methodFromClass = getMethodInClass(targetClass, targetMethod);

        Annotation[] methodAnnotations = methodFromClass.getDeclaredAnnotations();

        return getSecureAnnotationFromAllAnnotations(methodAnnotations);
    }

    private Method getMethodInClass(Class targetClass, Method targetMethod) throws NoSuchMethodException {
        Method methodFromClass = null;

        for (Method m : targetClass.getMethods()) {
            if (m.getName().equals(targetMethod.getName())) {
                methodFromClass = m;
            }
        }

        if (methodFromClass == null) {
            throw new NoSuchMethodException();
        }
        return methodFromClass;
    }

    private Secure getSecureAnnotationFromAllAnnotations(Annotation[] methodAnnotations) throws AnnotationNotFoundException {
        for (Annotation a : methodAnnotations) {
            if (a.annotationType() == Secure.class) {
                return (Secure) a;
            }
        }
        throw new AnnotationNotFoundException();
    }
}
