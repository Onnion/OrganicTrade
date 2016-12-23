package mpoo.bsi.ufrpe.organictrade.controler.item.dominio;
public class TentItem {

    private int tentItemsId;
    private Double value;
    private int currentAmount;
    private Product product;
    private Tent tent;
    private String unity;
    private byte[] imageItem;

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

    public int getTentItemsId() {
        return tentItemsId;
    }

    public void setTentItemsId(int tentItemsId) {
        this.tentItemsId = tentItemsId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double valor) {
        this.value = valor;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public byte[] getImageItem() {
        return imageItem;
    }

    public void setImageItem(byte[] imageItem) {
        this.imageItem = imageItem;
    }
}