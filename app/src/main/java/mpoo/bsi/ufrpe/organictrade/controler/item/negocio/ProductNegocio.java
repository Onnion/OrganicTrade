package mpoo.bsi.ufrpe.organictrade.controler.item.negocio;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.ProductPersistence;

public class ProductNegocio {
    private ProductPersistence productPerssitence = new ProductPersistence();

    public String[] getNameProducts() {
        return productPerssitence.getNameProducts();
    }

    public Product getProductById(int i) {
        return productPerssitence.getProductById(i);
    }

    public String idProductByName(String s) {
        return productPerssitence.idProductByName(s);
    }

    public String nameProductById(int productId) {
        return productPerssitence.nameProductById(productId);
    }

    public ArrayList<Product> getAllProducts() {
        return productPerssitence.getAllProducts();
    }
}
