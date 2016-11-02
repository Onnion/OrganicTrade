package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio;

public class Product {
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String pproduct_name) {
        this.product_name = pproduct_name;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    private String product_name;
    private String id_product;
}
