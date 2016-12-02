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
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();
    private final String[] nameProducts = {"Abacate","Abacaxi","Açaí","Acerola","Ameixa","Amora","Banana","Cajá","Caju","Carambola","Cereja","Coco","Cupuaçu","Damasco","Fruta Pão","Goiaba","Graviola","Jabuticaba","Jaca","Jambo","Laranja","Limão","Lichia","Maçã","Manga","Maracujá","Melância","Melão","Mexerica","Pêra","Pêssego","Pinha","Pinhão","Pitanga","Pitomba","Romã","Sapoti","Tamarindo","Tangerina","Tomate","Toranja","Umbu","Uva","Acelga","Agrião","Alcachofra","Alface","Aspargo","Brócolis","Cebolinha","Coentro","Couve","Espinafre","Hortelã","Manjericão","Mostarda","Rúcula","Salsa","Abóbora","Abobrinha","Alho","Berinjela","Beterraba","Cebola","Cenoura","Chuchu","Couve-flor","Ervilha","Fava","Feijão","Gengibre","Jiló","Milho","Nabo","Pepino","Pimenta","Pimentão","Quiabo","Rabanete","Repolho","Soja","Vagem"};
    private final String[] products = {"Abacate Fruta","Abacaxi Fruta","Açaí Fruta","Acerola Fruta","Ameixa Fruta","Amora Fruta","Banana Fruta","Cajá Fruta","Caju Fruta","Carambola Fruta","Cereja Fruta","Coco Fruta","Cupuaçu Fruta","Damasco Fruta","Fruta Pão Fruta","Goiaba Fruta","Graviola Fruta","Jabuticaba Fruta","Jaca Fruta","Jambo Fruta","Laranja Fruta","Limão Fruta","Lichia Fruta","Maçã Fruta","Manga Fruta","Maracujá Fruta","Melância Fruta","Melão Fruta","Mexerica Fruta","Pêra Fruta","Pêssego Fruta","Pinha Fruta","Pinhão Fruta","Pitanga Fruta","Pitomba Fruta","Romã Fruta","Sapoti Fruta","Tamarindo Fruta","Tangerina Fruta","Tomate Fruta","Toranja Fruta","Umbu Fruta","Uva Fruta","Acelga Verduras","Agrião Verduras","Alcachofra Verduras","Alface Verduras","Aspargo Verduras","Brócolis Verduras","Cebolinha Verduras","Coentro Verduras","Couve Verduras","Espinafre Verduras","Hortelã Verduras","Manjericão Verduras","Mostarda Verduras","Rúcula Verduras","Salsa Verduras","Abóbora Legumes","Abobrinha Legumes","Alho Legumes","Berinjela Legumes","Beterraba Legumes","Cebola Legumes","Cenoura Legumes","Chuchu Legumes","Couve-flor Legumes","Ervilha Legumes","Fava Legumes","Feijão Legumes","Gengibre Legumes","Jiló Legumes","Milho Legumes","Nabo Legumes","Pepino Legumes","Pimenta Legumes","Pimentão Legumes","Quiabo Legumes","Rabanete Legumes","Repolho Legumes","Soja Legumes","Vagem Legumes"};


    public String[] getNameProducts(){
        return nameProducts;
    }

    public Product createProduct(String name, String type) {
        Product product = new Product();
        product.setProductName(name);
        product.setProductType(type);
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
        valuesProduct.put(DatabaseHelper.getColumnProductType(), product.getProductType());
        db.insert(DatabaseHelper.getColumnProductType(), null, valuesProduct);
        db.close();
    }

    public void populateProductTable(){
        for (String product: products){
            String[] value = product.split(" ");
            registerProduct(createProduct(value[0], value[1]));
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
        String name = null;
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductNameById(),new String[]{id});
        if(cursor.moveToFirst()){
            name = cursor.getString(1);}
        cursor.close();
        db.close();
        return name;
    }

    public void productTableIsEmpty(){
        db=banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.productTableIsEmpty(),null);
        if(!(cursor.moveToFirst())){
            populateProductTable();}
        cursor.close();
        db.close();
    }

    public Product getProductByid(String productId) {
        db = banco.getReadableDatabase();
        Product product = null;
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductNameById(),new String[]{productId});
        if(cursor.moveToFirst()){
            product = createProductBtConsult(cursor);
        }
        cursor.close();
        db.close();
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
        cursor.close();
        db.close();
        return products;
    }
}