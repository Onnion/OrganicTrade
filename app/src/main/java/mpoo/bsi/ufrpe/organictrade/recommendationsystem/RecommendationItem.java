package mpoo.bsi.ufrpe.organictrade.recommendationsystem;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;

public class RecommendationItem implements Comparable<RecommendationItem> {
    private Product product;
    private float vote;

    public Product getProduct() {
        return product;
    }

    public RecommendationItem (Product produc, Float vot){
        product = produc;
        vote = vot;
    }

    @Override
    public int compareTo(RecommendationItem that) {
        int result;
        if (this.product.getProductId() == that.product.getProductId()) {
            result = 0;
        }else{
            if (this.vote < that.vote) {
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }
}
