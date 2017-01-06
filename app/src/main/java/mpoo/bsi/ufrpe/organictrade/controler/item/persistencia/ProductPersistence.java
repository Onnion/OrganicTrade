package mpoo.bsi.ufrpe.organictrade.controler.item.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;

public class ProductPersistence {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();
    private final String[] nameProducts = {"Abacate","Abacaxi","Açaí","Acerola","Ameixa","Amora","Banana","Cajá","Caju","Carambola","Cereja","Coco","Cupuaçu","Damasco","Fruta Pão","Goiaba","Graviola","Jabuticaba","Jaca","Jambo","Laranja","Limão","Lichia","Maçã","Manga","Maracujá","Melância","Melão","Mexerica","Pêra","Pêssego","Pinha","Pinhão","Pitanga","Pitomba","Romã","Sapoti","Tamarindo","Tangerina","Tomate","Toranja","Umbu","Uva","Acelga","Agrião","Alcachofra","Alface","Aspargo","Brócolis","Cebolinha","Coentro","Couve","Espinafre","Hortelã","Manjericão","Mostarda","Rúcula","Salsa","Abóbora","Abobrinha","Alho","Berinjela","Beterraba","Cebola","Cenoura","Chuchu","Couve-flor","Ervilha","Fava","Feijão","Gengibre","Jiló","Milho","Nabo","Pepino","Pimenta","Pimentão","Quiabo","Rabanete","Repolho","Soja","Vagem"};

    public String[] getNameProducts(){
        return nameProducts;
    }

    private Product createProductBtConsult(Cursor cursor){
        Product product = new Product();
        product.setProductId(cursor.getInt(0));
        product.setProductName(cursor.getString(1));
        product.setProductType(cursor.getString(2));
        product.setProductDescription(cursor.getString(3));
        product.setProductImg(cursor.getString(4));
        return product;
    }

    public String idProductByName(String productName){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductIdByName(),new String[]{productName});
        if(cursor.moveToFirst()){
            db.close();
            return cursor.getString(0);
            }
        else{
            db.close();
            cursor.close();
            return "";}
    }

    public String nameProductById(int id){
        db = banco.getReadableDatabase();
        String name = null;
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductNameById(),new String[]{Integer.toString(id)});
        if(cursor.moveToFirst()){
            name = cursor.getString(1);}
        cursor.close();
        db.close();
        return name;
    }

    public Product getProductById(int productId) {
        db = banco.getReadableDatabase();
        Product product = null;
        Cursor cursor = db.rawQuery(ComandosSql.sqlProductNameById(),new String[]{Integer.toString(productId)});
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
        Cursor cursor = db.rawQuery(ComandosSql.sqlGetAllProducts(),null);
        if(cursor.moveToFirst())
            do{
                products.add(createProductBtConsult(cursor));
            }while (cursor.moveToNext());
        cursor.close();
        db.close();
        return products;
    }

}