package mpoo.bsi.ufrpe.organictrade.controler.item.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItems;

public class TentItemsPersistence {
    private SQLiteDatabase db;//
    private DatabaseHelper banco = Session.getDbAtual();

    public TentItems createTentItems(Cursor cursor){
        TentItems tentItems = new TentItems();
        tentItems.setTentItemsId(cursor.getInt(0));
        tentItems.setCurrentAmount(cursor.getInt(1));
        tentItems.setValue(cursor.getDouble(2));
        tentItems.setUnity(cursor.getString(3));
        //--------------------------------------------------------------//
        ProductPersistence productPersistence = new ProductPersistence();
        Product product;
        product = productPersistence.getProductById(cursor.getInt(4));
        tentItems.setProduct(product);

        TentPersistence tentPersistence = new TentPersistence();
        Tent tent;
        tent = tentPersistence.getTent(cursor.getInt(5));
        tentItems.setTent(tent);
        //--------------------------------------------------------------//
        tentItems.setImageItem(cursor.getString(6));
        return tentItems;
    }

    public void deleteTentItems(int id){
        db = banco.getReadableDatabase();
        String where = DatabaseHelper.getColumnTentitemsId()+"=?;";
        db.delete(DatabaseHelper.getTableTentitemsName(),where,new String[]{Integer.toString(id)});
        db.close();
    }

    public void deleteAllItemsOfTent(int tentId){
        db = banco.getReadableDatabase();
        String where = DatabaseHelper.getColumnTentitemsTentId()+"=?;";
        db.delete(DatabaseHelper.getTableTentitemsName(),where,new  String[]{Integer.toString(tentId)});
        db.close();
    }

    public void insertTentItems(TentItems tentItems){
        db = banco.getWritableDatabase();
        ContentValues tentItemsValues = new ContentValues();
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsAmount(), tentItems.getCurrentAmount());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsPrice(), tentItems.getValue());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsUnity(), tentItems.getUnity());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsTentId(),tentItems.getTent().getTentId());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsProductId(),tentItems.getProduct().getProductId());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsImg(),tentItems.getImageItem());
        db.insert(DatabaseHelper.getTableTentitemsName(), null, tentItemsValues);
        db.close();
    }

    public void tentItemEdit(TentItems tentItems){
        db = banco.getWritableDatabase();
        String where = DatabaseHelper.getColumnTentitemsId()+" = "+Session.getItemSelected().getTentItemsId();
        ContentValues tentItemsEditedValues = new ContentValues();
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsAmount(),tentItems.getCurrentAmount());
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsPrice(),tentItems.getValue());
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsUnity(), tentItems.getUnity());
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsImg(), tentItems.getImageItem());
        db.update(DatabaseHelper.getTableTentitemsName(), tentItemsEditedValues,where,null);
        db.close();
    }

    public ArrayList<TentItems> getItemsOfTent(int tentId ){
        ArrayList<TentItems> tentItems = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlAllItemsOfTent(),new String[]{Integer.toString(tentId)});
        if(cursor.moveToFirst()){
            do{
                TentItems tentItem = createTentItems(cursor);
                tentItems.add(tentItem);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tentItems;
    }

    public ArrayList<TentItems> getAllItems(int userId){
        ArrayList<TentItems> tentItems = new ArrayList<>();
        db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery(ComandosSql.sqlAllItems(),new String[]{Integer.toString(userId),Integer.toString(userId)});

        if(cursor.moveToFirst()){
            do{
                TentItems tentItem = createTentItems(cursor);
                tentItems.add(tentItem);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tentItems;
    }

    public TentItems createTentItemsById(int anInt) {
        db = banco.getReadableDatabase();
        TentItems tentItem = null;
        Cursor cursor = db.rawQuery(ComandosSql.sqlTentItemById(),new String[]{Integer.toString(anInt)});
        if(cursor.moveToFirst()){
            tentItem = createTentItems(cursor);
        }
        return tentItem;
    }
}