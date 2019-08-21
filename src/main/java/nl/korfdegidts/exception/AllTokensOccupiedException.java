/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/29/18 11:22 AM
 */

package nl.korfdegidts.exception;

public class AllTokensOccupiedException extends RuntimeException {

    public AllTokensOccupiedException(String message) {
        super(message);
    }
}
