package courier;

public class CourierCreate{
    public CourierCreate(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    private String login;

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    private String password;

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    private String firstName;
}