package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.dominio;

public class User {

    private int userId;
    private String userName;
    private String password;
    private String email;
    private String name;
    private Long phone;
    private String image;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_user() {
        return userId;
    }

    public void setId_user(int id_user) {
        this.userId = id_user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}