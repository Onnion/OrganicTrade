package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio;

import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.dominio.User;

public class Tent {
    private String tentId;
    private User user;
    private String longi;
    private String lagi;
    private String name;
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLagi() {
        return lagi;
    }

    public void setLagi(String lagi) {
        this.lagi = lagi;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTentId() {
        return tentId;
    }

    public void setTentId(String tentId) {
        this.tentId = tentId;
    }
}