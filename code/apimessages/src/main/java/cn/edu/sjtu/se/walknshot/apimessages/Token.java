package cn.edu.sjtu.se.walknshot.apimessages;

public class Token {
    private int userId;
    private String passphrase;

    public Token(int userId, String passphrase) {
        this.userId = userId;
        this.passphrase = passphrase;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public static Token fromString(String str) {
        String[] parts = str.split(":");
        if (parts.length != 2)
            return null;
        try {
            return new Token(Integer.parseInt(parts[0]), parts[1]);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
