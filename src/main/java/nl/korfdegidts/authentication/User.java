package nl.korfdegidts.authentication;

public class User {
    private LoginCredentials credentials;
    private UserToken token;

    public User(LoginCredentials credentials) {
        this.credentials = credentials;
        token = new UserToken(credentials.getUser(), generateToken());
    }

    public LoginCredentials getCredentials() {
        return credentials;
    }

    public UserToken getToken() {
        return token;
    }

    private String generateToken() {
        return "1234-1234-1234";
    }
}
