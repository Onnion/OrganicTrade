package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.Infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Product;

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
    }//

    public void setId(int i) {
        this.id = i;
    }//

    public Product createProduct(String string) {
        Product product = new Product();
        product.setProductId(Integer.toString(getId()));
        product.setProductName(string);
        setId(getId()+1);
        return product;
    }

    private Product createProductBtConsult(Cursor cursor){
        Product product = new Product();
        product.setProductId(cursor.getString(0));
        product.setProductName(cursor.getString(1));
        return product;
    }

    public void registerProduct(Product product){
        db = banco.getWritableDatabase();
        ContentValues valuesProduct = new ContentValues();
        valuesProduct.put(DatabaseHelper.getColumnProductId(),product.getProductId());
        valuesProduct.put(DatabaseHelper.getColumnProductName(), product.getProductName());
        db.insert(DatabaseHelper.getTableProductName(), null, valuesProduct);
        db.close();
    }

    public void populateProductTable(){
        for (String string: nameProducts){
            registerProduct(createProduct(string));
        }
    }

    public ProductPersistence(){
        productTableIsEmpty();
    }

    public String idProductByName(String productName){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductIdByName(),new String[]{productName});
        if(cursor.moveToFirst()){return cursor.getString(0);}
        else{return "";}
    }

    public String nameProductById(String id){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductNameById(),new String[]{id});
        if(cursor.moveToFirst()){
            return cursor.getString(1);}
        else{return "";}
    }

    public void productTableIsEmpty(){
        db=banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.productTableIsEmpty(),null);
        if(!(cursor.moveToFirst())){
            populateProductTable();}
    }

    public Product getProductByid(String productId) {
        db = banco.getReadableDatabase();
        Product product = null;
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductNameById(),new String[]{productId});
        if(cursor.moveToFirst()){
            product = createProductBtConsult(cursor);
        }
        return product;
    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<>();
        db=banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlAllProducts(),null);
        if(cursor.moveToFirst())
            do{
                products.add(createProductBtConsult(cursor));
            }while (cursor.moveToNext());
        return products;
    }
}