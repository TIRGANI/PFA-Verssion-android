package emsi.pfa.smart_wattering_v0.ui.beans;

public class User {
    private int userId;
    private String email;
    private String password;
    private String username;

    private Role role;

    public User(int  userId, String username,String password, String email,  Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;

        this.role = role;
    }

    public User() { }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
