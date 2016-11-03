package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Product;

public class ProductPersistence {
    private int id = 0;
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();
    private final String[] nameProducts = {"Tomate","Banana","Maçã"};


    public String[] getNameProducts(){
        return nameProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public Product createProduct(String string) {
        Product product = new Product();
        product.setId_product(Integer.toString(getId()));
        product.setProduct_name(string);
        return product;
    }
    public void registerProduct(Product product){
        db = banco.getWritableDatabase();
        ContentValues valuesProduct = new ContentValues();
        valuesProduct.put(DatabaseHelper.getColumnUserId(),product.getId_product());
        valuesProduct.put(DatabaseHelper.getColumnUserUsername(), product.getProduct_name());
        db.insert(DatabaseHelper.getTableProductName(), null, valuesProduct);
        db.close();
    }

    public void populateProductTable(){
        for (String string: nameProducts){
            registerProduct(createProduct(string));
        }
    }

    public ProductPersistence(){
        populateProductTable();
    }
}