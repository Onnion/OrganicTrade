package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.List;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Product;
import mpoo.bsi.ufrpe.organictrade.R;

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
        setId(getId()+1);
        return product;
    }

    public void registerProduct(Product product){
        db = banco.getWritableDatabase();
        ContentValues valuesProduct = new ContentValues();
        valuesProduct.put(DatabaseHelper.getColumnProductId(),product.getId_product());
        valuesProduct.put(DatabaseHelper.getColumnProductName(), product.getProduct_name());
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

    public String idProductByName(String nomeDoProduto){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductIdByName(),new String[]{nomeDoProduto});
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
}