/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/31/18 10:45 PM
 */

package nl.korfdegidts.Authentication;

import nl.korfdegidts.exception.AnnotationNotFoundException;
import nl.korfdegidts.exception.InCorrectTargetMethodException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class SecureAnnotationChecker {

    public boolean methodIsSecured(Class targetClass, Method targetMethod) throws InCorrectTargetMethodException {

        methodAndClassAreSet(targetClass, targetMethod);

        try {
            Secure secureAnnotation = getAnnotationFromMethod(targetClass, targetMethod);

        } catch (NoSuchMethodException e) {
            throw new InCorrectTargetMethodException();
        } catch (AnnotationNotFoundException a) {
            return false;
        }
        return true;
    }

    public boolean userHasCorrectRoleForMethod(Class targetClass, Method targetMethod, Role role) throws InCorrectTargetMethodException {
        methodAndClassAreSet(targetClass, targetMethod);

        try {
            Secure sec = getAnnotationFromMethod(targetClass, targetMethod);
            for (Role annoRole : sec.allowedRoles()) {
                if (annoRole == role) {
                    System.out.println(annoRole);
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            return false;
        }

        return false;
    }

    private void methodAndClassAreSet(Class targetClass, Method targetMethod) throws InCorrectTargetMethodException {
        if (targetClass == null || targetMethod == null) {
            throw new InCorrectTargetMethodException();
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
