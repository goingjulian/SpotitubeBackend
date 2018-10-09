package nl.korfdegidts.entity;

public class Token implements IEntity {
    private String username;
    private String token;
    private long expiryDate;

    public Token(String username, String token, long expiryDate) {
        this.username = username;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public long getExpiryDate() {
        return expiryDate;
    }
}
