import java.util.Scanner;
public class User {
    private String URL;
    private String uname;
    private String password;

    public User(String URL, String uname, String password) {
        this.URL = URL;
        this.uname = uname;
        this.password = password;
    } // <--- User() constructor ends here

    // URL
    public String getURL(String URL) {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    // Username
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    // Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

} // <--- User{} class ends here
