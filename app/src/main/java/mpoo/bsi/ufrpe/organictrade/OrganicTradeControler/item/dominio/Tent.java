package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio;

import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.dominio.User;

public class Tent {
    private int tentId;
    private User user;
    private Double longi;
    private Double lagi;
    private String name;
    private String img;
    private int note;
    private int numberOfVotes;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

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

    public Double getLagi() {
        return lagi;
    }

    public void setLagi(Double lagi) {
        this.lagi = lagi;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTentId() {
        return tentId;
    }

    public void setTentId(int tentId) {
        this.tentId = tentId;
    }
}