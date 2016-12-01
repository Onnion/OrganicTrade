package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.Infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.TentItems;

public class TentItemsPersistence {
    private SQLiteDatabase db;//
    private DatabaseHelper banco = Session.getDbAtual();

    public TentItems createTentItems(Cursor cursor){
        TentItems tentItems = new TentItems();
        tentItems.setTentItemsId(cursor.getString(0));
        tentItems.setCurrentAmount(cursor.getString(1));
        tentItems.setValue(cursor.getString(2));
        tentItems.setUnity(cursor.getString(3));
        //--------------------------------------------------------------//
        String productId = cursor.getString(4);
        ProductPersistence productPersistence = new ProductPersistence();
        Product product;
        product = productPersistence.getProductByid(productId);
        tentItems.setProduct(product);

        String tentId = cursor.getString(5);
        TentPersistence tentPersistence = new TentPersistence();
        Tent tent;
        tent = tentPersistence.getTent(tentId);
        tentItems.setTent(tent);
        //--------------------------------------------------------------//
        tentItems.setImageItem(cursor.getString(6));
        return tentItems;
    }

    public void deleteTentItems(String id){
        db = banco.getReadableDatabase();
        String where = DatabaseHelper.getColumnTentitemsId()+"=?;";
        db.delete(DatabaseHelper.getTableTentitemsName(),where,new String[]{id});
        db.close();
    }

    public void deleteAllItemsOfTent(String tentId){
        db = banco.getReadableDatabase();
        String where = DatabaseHelper.getColumnTentitemsTentId()+"=?;";
        db.delete(DatabaseHelper.getTableTentitemsName(),where,new  String[]{tentId});
        db.close();
    }

    public void insertTentItems(TentItems tentItems){
        db = banco.getWritableDatabase();
        ContentValues tentItemsValues = new ContentValues();
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsId(), tentItems.getTentItemsId());
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

    public ArrayList<TentItems> getItemsOfTent(String tentId ){
        ArrayList<TentItems> tentItems = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlAllItemsOfTent(),new String[]{tentId});
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

    public ArrayList<TentItems> getAllItems(String userId){
        ArrayList<TentItems> tentItems = new ArrayList<>();
        db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery(ComandosSql.sqlAllItems(),new String[]{userId});

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
}