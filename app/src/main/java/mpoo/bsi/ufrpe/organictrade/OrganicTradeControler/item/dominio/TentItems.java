package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio;
public class TentItems {

    private String tentItems_id;
    private String value;
    private String currentAmount;
    private Product product;
    private Tent tent;
    private String unity;
    private String imageItem;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Tent getTent() {
        return tent;
    }

    public void setTent(Tent tent) {
        this.tent = tent;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
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