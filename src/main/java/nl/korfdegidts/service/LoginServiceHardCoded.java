package nl.korfdegidts.service;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;

import javax.inject.Named;

@Named("hardCoded")
public class LoginServiceHardCoded implements ILoginService {
    //Replace with db
    User hardCodedUser = new User(new UserCredentials("julian", "pass"));

    @Override
    public User getUserFromToken(String token) throws UserNotFoundException {
        //Replace with db
        if (hardCodedUser.getToken().equals(token)) return hardCodedUser;

        throw new UserNotFoundException();
    }

    @Override
    public User getUserFromCredentials(UserCredentials credentials) throws UserNotFoundException {
        //replace with db
        if (credentials.getUser().equals(hardCodedUser.getCredentials().getUser())
                && credentials.getPassword().equals(hardCodedUser.getCredentials().getPassword()))
            return hardCodedUser;

        throw new UserNotFoundException();
    }
}
