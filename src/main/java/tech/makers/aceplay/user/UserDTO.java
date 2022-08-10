package tech.makers.aceplay.user;

public class UserDTO {

    private String username;

    private String password;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
