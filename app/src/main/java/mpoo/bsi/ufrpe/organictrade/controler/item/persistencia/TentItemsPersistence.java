package mpoo.bsi.ufrpe.organictrade.controler.item.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Tent;

public class TentItemsPersistence {
    private SQLiteDatabase db;//
    private DatabaseHelper banco = Session.getDbAtual();

    public TentItem createTentItems(Cursor cursor){
        TentItem tentItem = new TentItem();
        tentItem.setTentItemsId(cursor.getInt(0));
        tentItem.setCurrentAmount(cursor.getInt(1));
        tentItem.setValue(cursor.getDouble(2));
        tentItem.setUnity(cursor.getString(3));
        //--------------------------------------------------------------//
        ProductPersistence productPersistence = new ProductPersistence();
        Product product;
        product = productPersistence.getProductById(cursor.getInt(4));
        tentItem.setProduct(product);

        TentPersistence tentPersistence = new TentPersistence();
        Tent tent;
        tent = tentPersistence.getTent(cursor.getInt(5));
        tentItem.setTent(tent);
        //--------------------------------------------------------------//
        tentItem.setImageItem(cursor.getBlob(6));
        return tentItem;
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

    public void insertTentItems(TentItem tentItem){
        db = banco.getWritableDatabase();
        ContentValues tentItemsValues = new ContentValues();
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsAmount(), tentItem.getCurrentAmount());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsPrice(), tentItem.getValue());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsUnity(), tentItem.getUnity());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsTentId(), tentItem.getTent().getTentId());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsProductId(), tentItem.getProduct().getProductId());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsImg(), tentItem.getImageItem());
        db.insert(DatabaseHelper.getTableTentitemsName(), null, tentItemsValues);
        db.close();
    }

    public void tentItemEdit(TentItem tentItem){
        db = banco.getWritableDatabase();
        String where = DatabaseHelper.getColumnTentitemsId()+" = "+Session.getItemSelected().getTentItemsId();
        ContentValues tentItemsEditedValues = new ContentValues();
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsAmount(), tentItem.getCurrentAmount());
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsPrice(), tentItem.getValue());
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsUnity(), tentItem.getUnity());
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsImg(), tentItem.getImageItem());
        db.update(DatabaseHelper.getTableTentitemsName(), tentItemsEditedValues,where,null);
        db.close();
    }

    public ArrayList<TentItem> getItemsOfTent(int tentId ){
        ArrayList<TentItem> tentItems = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlGetAllItemsOfTent(),new String[]{Integer.toString(tentId)});
        if(cursor.moveToFirst()){
            do{
                TentItem tentItem = createTentItems(cursor);
                tentItems.add(tentItem);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tentItems;
    }

    public ArrayList<TentItem> getAllItems(int userId){
        ArrayList<TentItem> tentItems = new ArrayList<>();
        db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery(ComandosSql.sqlGetAllItems(),new String[]{Integer.toString(userId),Integer.toString(userId)});

        if(cursor.moveToFirst()){
            do{
                TentItem tentItem = createTentItems(cursor);
                tentItems.add(tentItem);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tentItems;
    }

    public TentItem createTentItemsById(int anInt) {
        db = banco.getReadableDatabase();
        TentItem tentItem = null;
        Cursor cursor = db.rawQuery(ComandosSql.sqlSearchTentItemById(),new String[]{Integer.toString(anInt)});
        if(cursor.moveToFirst()){
            tentItem = createTentItems(cursor);
        }
        return tentItem;
    }
}