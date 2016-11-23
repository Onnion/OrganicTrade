package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio;

public class TentItems {

    private String tentItems_id;
    private String value;
    private String currentAmount;
    private String productId;
    private String user_id;
    private String imageItem = "0";

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    private String unity;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTentItems_id() {
        return tentItems_id;
    }

    public void setTentItems_id(String tentItems_id) {
        this.tentItems_id = tentItems_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String valor) {
        this.value = valor;
    }

    public String getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }


    public String getImageItem() {
        return imageItem;
    }

    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }
}
