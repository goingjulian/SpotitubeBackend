package nl.korfdegidts.service;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.dto.TokenDTO;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;

public interface ILoginService {
    TokenDTO getTokenDTOFromCredentials(UserCredentials credentials) throws UserNotFoundException;

    User getUserFromToken(String token) throws UserNotFoundException;
}
