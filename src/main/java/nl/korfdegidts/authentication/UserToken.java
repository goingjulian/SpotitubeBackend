package nl.korfdegidts.authentication;

public class UserToken {
    private String user;
    private String token;

    public UserToken() {
    }


    public UserToken(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUserName() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStringToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
